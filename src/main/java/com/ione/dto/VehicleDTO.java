package com.ione.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VehicleDTO {
    private Integer id;
    private String licensePlate;
    private String model;
    private String type;
    private int yearOfManufacture;
    private float capacityTons;
    private float volumeCubicMeters;
    private String vin;
    private Boolean isFree;
    private LocalDateTime createdAt;
    private LocalDate insuranceValidUntil;
    private String registrationCertificate;
    private Integer driverId;
}