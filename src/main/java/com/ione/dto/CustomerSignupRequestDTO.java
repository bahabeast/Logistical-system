package com.ione.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerSignupRequestDTO {

    @NotBlank
    private String companyName;

    @NotBlank
    private String contactPerson;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String companyAddress;
}
