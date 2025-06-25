package com.ione.repository;

import com.ione.entity.Vehicle;
import com.ione.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByIsFreeTrue();
    List<Vehicle> findByOwner(Driver owner);
    List<Vehicle> findAllByIsFreeTrue();
}
