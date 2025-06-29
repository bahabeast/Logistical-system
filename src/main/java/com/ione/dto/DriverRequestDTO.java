package com.ione.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class DriverRequestDTO {
    private String fullName;
    private String phoneNumber;
    @Email
    private String email;
    private LocalDate datOfBirth;
    private String licenseNumber;
    private LocalDateTime created_at;
    private BigDecimal balance;
    private String passwordHash;
}
