package com.ione.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponseDTO {

    private Integer id;
    private String companyName;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private String companyAddress;
    private LocalDateTime createdAt;
}
