package com.ione.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String companyName;
    private String contactPerson;
    private String phoneNumber;
    private String email;
    private LocalDateTime createdAt;
    private String companyAddress;
}
