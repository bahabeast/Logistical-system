package com.ione.repository;

import com.ione.entity.Order;
import com.ione.entity.Customer;
import com.ione.entity.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByStatus(DeliveryStatus status);
    List<Order> findByCustomerId(Integer customerId);

}
