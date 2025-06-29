package com.ione.dto;

import com.ione.entity.enums.DeliveryStatus;
import com.ione.entity.enums.VehicleType;
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
    private DeliveryStatus deliveryStatus;
    private String recipientPhone;
    private LocalDateTime createdAt;
    private VehicleType vehicleType;
}
