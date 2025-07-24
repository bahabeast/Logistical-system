package com.ione.controller;

import com.ione.dto.CustomerRequestDTO;
import com.ione.dto.CustomerResponseDTO;
import com.ione.security.AuthUtil;
import com.ione.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO created = customerService.createCustomer(dto);
        return ResponseEntity.ok(created);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Integer id) {
        CustomerResponseDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        String email = AuthUtil.getCurrentUsername();
        if (customerService.isNotOwner(email, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("/{id}/balance")
    public ResponseEntity<String> updateBalance(@PathVariable Integer id, @RequestParam BigDecimal amount) {
        String email = AuthUtil.getCurrentUsername();
        if (customerService.isNotOwner(email, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        customerService.updateBalance(id, amount);
        return ResponseEntity.ok("Customer balance updated");
    }
}
