package com.ione.controller;

import com.ione.entity.Order;
import com.ione.entity.enums.DeliveryStatus;
import com.ione.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/{customerId}")
    public Order createOrder(@Valid @PathVariable Integer customerId, @RequestBody Order order) {
        return orderService.createOrder(order,customerId);
    }
    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Integer customerId) {
        return orderService.getByCustomerId(customerId);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/{orderId}/assign/{vehicleId}")
    public Order assignVehicle(@Valid @PathVariable Long orderId, @PathVariable Integer vehicleId) {
        return orderService.assignVehicle(orderId, vehicleId);
    }
    @PatchMapping("/{orderId}/deliveryStatus")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long orderId,
            @RequestParam("deliveryStatus") DeliveryStatus newStatus) {

        Order updatedOrder = orderService.updateDeliveryStatus(orderId, newStatus);
        return ResponseEntity.ok(updatedOrder);
    }
}
