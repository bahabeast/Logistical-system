package com.ione.repository;

import com.ione.entity.Consignment;
import com.ione.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsignmentRepository extends JpaRepository<Consignment, Long> {
    Consignment findByOrder(Order order);
}
