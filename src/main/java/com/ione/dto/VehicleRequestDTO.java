package com.ione.dto;

import com.ione.entity.enums.VehicleType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VehicleRequestDTO {
    private String licencePlate;
    private String model;
    private VehicleType vehicleType;
    private int yearOfManufacture;
    private float capacityTons;
    private float volumeCubicMeters;
    private String vin;
    private Boolean isFree;
    private LocalDate insuranceValidUntil;
    private String registrationCertificate;
    private Integer driverId;
    private LocalDateTime createdAt;
}
