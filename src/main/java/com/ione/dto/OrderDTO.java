package com.ione.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long customerId;
    private String cargoDescription;
    private float weightTons;
    private float volumeCubicMeters;
    private String pickupLocation;
    private String deliveryLocation;
    private Long vehicleId;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private String status;
    private LocalDateTime createdAt;
}
