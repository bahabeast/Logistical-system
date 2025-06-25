package com.ione.controller;

import com.ione.entity.Order;
import com.ione.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/{customerId}")
    public Order createOrder(@Valid @PathVariable Integer customerId, @RequestBody Order order) {
        return orderService.createOrder(order,customerId);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Integer customerId) {
        return orderService.getByCustomerId(customerId);
    }

    @PostMapping("/{orderId}/assign/{vehicleId}")
    public Order assignVehicle(@Valid @PathVariable Long orderId, @PathVariable Integer vehicleId) {
        return orderService.assignVehicle(orderId, vehicleId);
    }

    @PostMapping("/{orderId}/mark-success")
    public Order markAsSucceed(@Valid @PathVariable Long orderId) {
        return orderService.markAsSucceed(orderId);
    }

    @PostMapping("/{orderId}/mark-failed")
    public Order markAsFailed(@Valid @PathVariable Long orderId) {
        return orderService.markAsFailed(orderId);
    }
}
