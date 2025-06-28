package com.ione.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long customerId;
    private String cargoDescription;
    private float weightTons;
    private float volumeCubicMeters;
    private String pickupLocation;
    private String deliveryLocation;
    private Long vehicleId; // optional
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
}
