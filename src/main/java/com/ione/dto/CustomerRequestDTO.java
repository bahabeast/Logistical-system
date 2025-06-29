package com.ione.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CustomerRequestDTO {

    private String companyName;
    private LocalDateTime createdAt;
    private String contactPerson;
    @Email
    private String email;
    private String phoneNumber;
    private String companyAddress;
    private String passwordHash;
    private BigDecimal balance;
}
