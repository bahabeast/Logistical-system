package com.ione.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ConsignmentResponseDTO {
    private Long id;
    private Long orderId;
    private BigDecimal totalCost;
    private BigDecimal platformFee;
    private BigDecimal driverPayment;
    private String fileUrl;
    private LocalDateTime issuedAt;
    private LocalDateTime deliveredAt;
}
