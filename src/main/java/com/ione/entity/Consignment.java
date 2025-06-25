package com.ione.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "consignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consignment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;

    @Column(name = "total_cost", nullable = false, precision = 13, scale = 3)
    private BigDecimal totalCost;

    @Column(name = "platform_fee", nullable = false, precision = 13, scale = 3)
    private BigDecimal platformFee;

    @Column(name = "driver_payment", nullable = false, precision = 13, scale = 3)
    private BigDecimal driverPayment;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;

    @Column(name = "delivered_at", nullable = false)
    private LocalDateTime deliveredAt;

    @PrePersist
    protected void onCreate() {
        issuedAt = LocalDateTime.now();
    }
}
