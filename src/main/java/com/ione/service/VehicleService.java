package com.ione.service;
import com.ione.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle createVehicle(Vehicle vehicle, Integer driverId);
    Vehicle getVehicleById(Integer id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getFreeVehicles();
    void deleteVehicle(Integer id);
}
