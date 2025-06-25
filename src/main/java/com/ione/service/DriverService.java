package com.ione.service;

import com.ione.entity.Driver;

import java.util.List;

public interface DriverService {
    Driver createDriver(Driver driver);
    Driver getDriverById(Integer id);
    List<Driver> getAllDrivers();
    void deleteDriver(Integer id);
}
