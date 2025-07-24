package com.ione.controller;

import com.ione.dto.DriverRequestDTO;
import com.ione.dto.DriverResponseDTO;
import com.ione.security.AuthUtil;
import com.ione.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverResponseDTO> createDriver(@Valid @RequestBody DriverRequestDTO dto) {
        DriverResponseDTO created = driverService.createDriver(dto);
        return ResponseEntity.ok(created);
    }
    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> getDriverById(@PathVariable Integer id) {
        DriverResponseDTO driver = driverService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @GetMapping
    public ResponseEntity<List<DriverResponseDTO>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @PreAuthorize("hasRole('DRIVER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {
        String email = AuthUtil.getCurrentUsername();
        if (driverService.isNotOwner(email, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('DRIVER')")
    @PatchMapping("/{id}/balance")
    public ResponseEntity<String> updateBalance(@PathVariable Integer id, @RequestParam BigDecimal amount) {
        String email = AuthUtil.getCurrentUsername();
        if (driverService.isNotOwner(email, id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        driverService.updateBalance(id, amount);
        return ResponseEntity.ok("Driver balance updated");
    }
}
