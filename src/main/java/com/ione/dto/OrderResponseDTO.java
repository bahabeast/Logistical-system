package com.ione.dto;

import com.ione.entity.enums.DeliveryStatus;
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
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private DeliveryStatus status;
    private LocalDateTime createdAt;
}
