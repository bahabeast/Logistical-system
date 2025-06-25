package com.ione.service;

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

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public Order updateStatus(Long orderId, DeliveryStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
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
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
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
}
