package com.ione.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class DriverResponseDTO {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private LocalDate datOfBirth;
    private String licenseNumber;
    private LocalDateTime created_at;
}
