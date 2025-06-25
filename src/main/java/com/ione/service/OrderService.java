package com.ione.service;

import com.ione.entity.Order;
import com.ione.entity.Customer;
import com.ione.entity.Vehicle;
import com.ione.entity.enums.DeliveryStatus;

import java.util.List;

public interface OrderService {
    Order updateStatus(Long orderId, DeliveryStatus status);
    Order markAsFailed(Long orderId);
    Order markAsSucceed(Long orderId); // creates Consignment
    List<Order> getCustomerOrders(Integer customerId);
    Order createOrder(Order order, Integer customerId);

    Order getById(Long id);

    List<Order> getByCustomerId(Integer customerId);

    Order assignVehicle(Long orderId, Integer vehicleId);
}
