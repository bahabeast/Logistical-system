package com.ione.mapper;

import com.ione.dto.DriverRequestDTO;
import com.ione.dto.DriverResponseDTO;
import com.ione.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    public static Driver toEntity(DriverRequestDTO dto){
        Driver driver = new Driver();
        driver.setFullName(dto.getFullName());
        driver.setEmail(dto.getEmail());
        driver.setCreatedAt(dto.getCreated_at());
        driver.setDateOfBirth(dto.getDatOfBirth());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setLicenseNumber(dto.getLicenseNumber());
        return driver;
    }
    public static DriverResponseDTO toDTO(Driver driver){
        DriverResponseDTO dto = new DriverResponseDTO();
        dto.setId(driver.getId());
        dto.setEmail(driver.getEmail());
        dto.setCreated_at(driver.getCreatedAt());
        dto.setLicenseNumber(driver.getLicenseNumber());
        dto.setPhoneNumber(driver.getPhoneNumber());
        dto.setDatOfBirth(driver.getDateOfBirth());
        dto.setFullName(driver.getFullName());
        return dto;
    }
}
