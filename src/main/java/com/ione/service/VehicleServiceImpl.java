package com.ione.service;
import com.ione.entity.Driver;
import com.ione.entity.Vehicle;
import com.ione.repository.DriverRepository;
import com.ione.repository.VehicleRepository;
import com.ione.service.VehicleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    @Override
    @Transactional
    public Vehicle createVehicle(Vehicle vehicle, Integer driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        vehicle.setOwner(driver);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Integer id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getFreeVehicles() {
        return vehicleRepository.findAllByIsFreeTrue();
    }

    @Override
    @Transactional
    public void deleteVehicle(Integer id) {
        vehicleRepository.deleteById(id);
    }
}
