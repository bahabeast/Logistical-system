package com.ione.dto;

import com.ione.entity.enums.DeliveryStatus;
import com.ione.entity.enums.VehicleType;
import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long customerId;
    private String cargoDescription;
    private float weightTons;
    private float volumeCubicMeters;
    private String pickupLocation;
    private String deliveryLocation;
    private Long vehicleId;
    private DeliveryStatus deliveryStatus;
    private String recipientPhone;
    private VehicleType vehicleType;
}
