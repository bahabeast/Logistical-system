package com.ione.repository;

import com.ione.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    boolean existsByEmail(String email);
    Driver findByEmail(String email);
}
