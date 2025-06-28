package com.ione.dto;

import com.ione.entity.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VehicleResponseDTO {
    private Integer id;
    private String fullName;
    private String model;
    private VehicleType type;
    private int yearOfManufacture;
    private float capacityTons;
    private float volumeCubicMeters;
    private String vin;
    private Boolean isFree;
    private LocalDateTime createdAt;
    private LocalDate insuranceValidUntil;
    private String registrationCertificate;
    private String licensePlate;
    private Integer driverId;
}
