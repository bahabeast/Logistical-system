package com.ione.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private LocalDate datOfBirth;
    private String licenseNumber;
    private LocalDateTime created_at;
}
