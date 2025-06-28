package com.ione.service;
import com.ione.security.AuthUtil;
import com.ione.security.JwtUtil;
import com.ione.entity.Driver;
import com.ione.entity.enums.Role;
import org.springframework.security.access.AccessDeniedException;

import com.ione.entity.Customer;
import com.ione.entity.Order;
import com.ione.entity.Vehicle;
import com.ione.entity.enums.DeliveryStatus;
import com.ione.repository.CustomerRepository;
import com.ione.repository.OrderRepository;
import com.ione.repository.VehicleRepository;
import com.ione.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public Order updateStatus(Long orderId, DeliveryStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        String username = AuthUtil.getCurrentUsername();
        Role role = Role.valueOf(AuthUtil.getCurrentUserRole());
        DeliveryStatus currentStatus = order.getStatus();

        if (role == Role.CUSTOMER) {
            if (!order.getCustomer().getEmail().equals(username)) {
                throw new AccessDeniedException("Not your order.");
            }

            // Allowed transitions: PENDING → GOING_TO_LOAD
            // DELIVERED → UNLOADING → SUCCEED/FAILED
            boolean valid = switch (currentStatus) {
                case PENDING -> newStatus == DeliveryStatus.GOING_TO_LOAD;
                case DELIVERED -> newStatus == DeliveryStatus.UNLOADING;
                case UNLOADING -> newStatus == DeliveryStatus.SUCCEED || newStatus == DeliveryStatus.FAILED;
                default -> false;
            };

            if (!valid)
                throw new IllegalStateException("Customer can't perform this status change.");
        }

        if (role == Role.DRIVER) {
            if (order.getVehicle() == null ||
                    !order.getVehicle().getOwner().getEmail().equals(username)) {
                throw new AccessDeniedException("Driver doesn't own this order.");
            }

            // Allowed transitions: GOING_TO_LOAD → LOADING → IN_TRANSIT → DELIVERED
            boolean valid = switch (currentStatus) {
                case GOING_TO_LOAD -> newStatus == DeliveryStatus.LOADING;
                case LOADING -> newStatus == DeliveryStatus.IN_TRANSIT;
                case IN_TRANSIT -> newStatus == DeliveryStatus.DELIVERED;
                default -> false;
            };

            if (!valid)
                throw new IllegalStateException("Driver can't perform this status change.");
        }

        order.setStatus(newStatus);
        return orderRepository.save(order);
    }


    @Override
    @Transactional
    public Order markAsFailed(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(DeliveryStatus.FAILED);
        // No consignment will be created
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order markAsSucceed(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(DeliveryStatus.SUCCEED);
        // Logic for consignment should be triggered elsewhere after this
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getCustomerOrders(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }


    @Override
    @Transactional
    public Order createOrder(Order order, Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        String username = AuthUtil.getCurrentUsername();
        Role role = Role.valueOf(AuthUtil.getCurrentUserRole());

        if (role == Role.CUSTOMER) {
            if (!order.getCustomer().getEmail().equals(username)) {
                throw new AccessDeniedException("Access denied to this order");
            }
        } else if (role == Role.DRIVER) {
            if (order.getVehicle() == null ||
                    !order.getVehicle().getOwner().getEmail().equals(username)) {
                throw new AccessDeniedException("Driver can't access this order");
            }
        }

        // only allow PENDING orders for customers/drivers
        if ((role == Role.CUSTOMER || role == Role.DRIVER) &&
                order.getStatus() != DeliveryStatus.PENDING &&
                role == Role.CUSTOMER) {
            throw new AccessDeniedException("Customers can only access PENDING orders");
        }

        return order;
    }


    @Override
    public List<Order> getByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public Order assignVehicle(Long orderId, Integer vehicleId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        order.setVehicle(vehicle);
        return orderRepository.save(order);
    }
    public Map<String, List<Order>> groupPendingOrdersByDestination() {
        return orderRepository.findByStatus(DeliveryStatus.PENDING)
                .stream()
                .collect(Collectors.groupingBy(Order::getDeliveryLocation));
    }

    public Map<String, List<Vehicle>> groupFreeVehiclesByType() {
        return vehicleRepository.findAllByIsFreeTrue()
                .stream()
                .collect(Collectors.groupingBy(v -> v.getType().name()));
    }

}
