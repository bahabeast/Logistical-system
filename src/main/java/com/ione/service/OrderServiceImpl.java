package com.ione.service;
import com.ione.entity.Consignment;
import com.ione.repository.ConsignmentRepository;
import com.ione.security.AuthUtil;
import com.ione.entity.enums.Role;
import org.springframework.security.access.AccessDeniedException;

import com.ione.entity.Customer;
import com.ione.entity.Order;
import com.ione.entity.Vehicle;
import com.ione.entity.enums.DeliveryStatus;
import com.ione.repository.CustomerRepository;
import com.ione.repository.OrderRepository;
import com.ione.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Order updateDeliveryStatus(Long orderId, DeliveryStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        String username = AuthUtil.getCurrentUsername();
        Role role = Role.valueOf(AuthUtil.getCurrentUserRole());
        DeliveryStatus currentStatus = order.getDeliveryStatus();

        // Role-based access validation
        if (role == Role.CUSTOMER) {
            if (!order.getCustomer().getEmail().equals(username)) {
                throw new AccessDeniedException("Not your order.");
            }
        } else if (role == Role.DRIVER) {
            if (order.getVehicle() == null ||
                    !order.getVehicle().getOwner().getEmail().equals(username)) {
                throw new AccessDeniedException("Driver doesn't own this order.");
            }
        }

        // Unified transition validation logic
        if (!currentStatus.canTransitionTo(newStatus, role)) {
            throw new IllegalStateException("Invalid status change: " + currentStatus + " → " + newStatus + " by " + role);
        }
        if (newStatus == DeliveryStatus.SUCCEED) {
            order.setDeliveryStatus(newStatus);
            order.getVehicle().setIsFree(true);
        }
            return orderRepository.save(order);


    }


    @Override
    @Transactional
    public Order markAsFailed(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setDeliveryStatus(DeliveryStatus.FAILED);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order markAsSucceed(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setDeliveryStatus(DeliveryStatus.SUCCEED);
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
                order.getDeliveryStatus() != DeliveryStatus.PENDING &&
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
        vehicle.setIsFree(false);
        order.setVehicle(vehicle);
        order.setDeliveryStatus(DeliveryStatus.GOING_TO_LOAD);
        return orderRepository.save(order);
    }
    public Map<String, List<Order>> groupPendingOrdersByDestination() {
        return orderRepository.findByDeliveryStatus(DeliveryStatus.PENDING)
                .stream()
                .collect(Collectors.groupingBy(Order::getDeliveryLocation));
    }

    public Map<String, List<Vehicle>> groupFreeVehiclesByType() {
        return vehicleRepository.findAllByIsFreeTrue()
                .stream()
                .collect(Collectors.groupingBy(v -> v.getVehicleType().name()));
    }

}
