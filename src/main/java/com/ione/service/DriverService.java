package com.ione.service;

import com.ione.entity.Driver;

import java.math.BigDecimal;
import java.util.List;

import com.ione.dto.DriverRequestDTO;
import com.ione.dto.DriverResponseDTO;

import java.util.List;

public interface DriverService {
    DriverResponseDTO createDriver(DriverRequestDTO dto);
    DriverResponseDTO getDriverById(Integer id);
    List<DriverResponseDTO> getAllDrivers();
    void deleteDriver(Integer id);
    void updateBalance(Integer driverId, BigDecimal amount);
}
