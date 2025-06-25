package com.ione.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="full_name",nullable = false)
    private String fullName;
    @Column(name="phone_number",nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(name="password_hash",nullable = false)
    private String passwordHash;
    @Column(name="date_of_birth",nullable = false)
    private LocalDate dateOfBirth;
    @Column(name="license_number",nullable = false)
    private String licenseNumber;
    @Column(precision = 13, scale = 3, nullable=false)
    private BigDecimal balance=BigDecimal.ZERO;
    @Column(name = "created_at",nullable = true)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<Vehicle> vehicles;
}
