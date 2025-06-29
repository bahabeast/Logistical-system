package com.ione.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsignmentDTO {
    private Long id;
    private Long orderId;
    private BigDecimal totalCost;
    private BigDecimal platformFee;
    private BigDecimal driverPayment;
    private String fileUrl;
    private LocalDateTime issuedAt;
}
