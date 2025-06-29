package com.ione.dto;

import com.ione.entity.enums.DeliveryStatus;
import com.ione.entity.enums.VehicleType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDTO {
    private Long id;
    private String customerCompanyName;
    private String cargoDescription;
    private float weightTons;
    private float volumeCubicMeters;
    private String pickupLocation;
    private String deliveryLocation;
    private String vehicleRegistrationNumber;
    private String recipientPhone;
    private LocalDateTime createdAt;
    private DeliveryStatus deliveryStatus;
    private VehicleType vehicleType;
}
