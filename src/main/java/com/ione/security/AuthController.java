package com.ione.security;

import com.ione.dto.*;
import com.ione.dto.AuthRequest;
import com.ione.entity.Customer;
import com.ione.entity.Driver;
import com.ione.repository.CustomerRepository;
import com.ione.repository.DriverRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/signup/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody @Valid CustomerSignupRequestDTO request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already registered.");
        }

        Customer customer = new Customer();
        customer.setCompanyName(request.getCompanyName());
        customer.setContactPerson(request.getContactPerson());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setCompanyAddress(request.getCompanyAddress());
        customer.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        customerRepository.save(customer);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/signup/driver")
    public ResponseEntity<?> registerDriver(@RequestBody @Valid DriverSignupRequestDTO request) {
        if (driverRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already registered.");
        }

        Driver driver = new Driver();
        driver.setFullName(request.getFullName());
        driver.setEmail(request.getEmail());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        driverRepository.save(driver);
        return ResponseEntity.ok("Driver registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Optional<Customer> customerOpt = Optional.ofNullable(customerRepository.findByEmail(request.getEmail()));
        Optional<Driver> driverOpt = Optional.ofNullable(driverRepository.findByEmail(request.getEmail()));

        String token;
        if (customerOpt.isPresent()) {
            token = jwtService.generateToken(customerOpt.get());
        } else if (driverOpt.isPresent()) {
            token = jwtService.generateToken(driverOpt.get());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
