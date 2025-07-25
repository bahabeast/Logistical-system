package com.ione.entity;

import com.ione.entity.enums.DeliveryStatus;
import com.ione.entity.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "cargo_description", nullable = false)
    private String cargoDescription;

    @Column(name = "weight_tons", nullable = false)
    private float weightTons;

    @Column(name = "volume_cubic_meters", nullable = false)
    private float volumeCubicMeters;

    @Column(name = "pickup_location", nullable = false)
    private String pickupLocation;

    @Column(name = "delivery_location", nullable = false)
    private String deliveryLocation;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle=null;

    @Column(name = "recipient_phone", nullable = false)
    private String recipientPhone;
    @Column(name="vehicle_type", nullable = false)
    @Enumerated
    private VehicleType vehicleType;
    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
