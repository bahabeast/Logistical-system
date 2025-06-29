package com.ione.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ione.entity.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "model", nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "year_of_manufacture", nullable = false)
    private int yearOfManufacture;

    @Column(name = "capacity_tons", nullable = false)
    private float capacityTons;

    @Column(name = "volume_cubic_meters", nullable = false)
    private float volumeCubicMeters;

    @Column(name = "vin", nullable = false)
    private String vin;

    @Column(name = "is_free", nullable = false)
    private Boolean isFree = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "insurance_valid_until", nullable = false)
    private LocalDate insuranceValidUntil;

    @Column(name = "registration_certificate", nullable = false)
    private String registrationCertificate;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    private Driver owner;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
