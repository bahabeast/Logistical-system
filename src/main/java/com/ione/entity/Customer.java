package com.ione.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "contact_person", nullable = false)
    private String contactPerson;
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "company_address", nullable = false)
    private String companyAddress;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(precision = 13, scale = 3)
    private BigDecimal balance=BigDecimal.ZERO;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
