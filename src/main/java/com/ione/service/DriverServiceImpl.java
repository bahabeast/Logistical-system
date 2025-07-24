package com.ione.service;

import com.ione.dto.DriverRequestDTO;
import com.ione.dto.DriverResponseDTO;
import com.ione.entity.Driver;
import com.ione.mapper.DriverMapper;
import com.ione.repository.DriverRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    public boolean isOwner(String email, Integer id) {
        return driverRepository.findById(id)
                .map(driver -> driver.getEmail().equalsIgnoreCase(email))
                .orElse(false);
    }

    @Override
    @Transactional
    public DriverResponseDTO createDriver(DriverRequestDTO dto) {
        Driver driver = DriverMapper.toEntity(dto);
        return DriverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public DriverResponseDTO getDriverById(Integer id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return DriverMapper.toDTO(driver);

    }
    @Override
    public void updateBalance(Integer driverId, BigDecimal amount) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        BigDecimal newBalance = driver.getBalance().add(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient balance. Cannot go below 0.");
        }

        driver.setBalance(newBalance);
        driverRepository.save(driver);
    }
    @Override
    public List<DriverResponseDTO> getAllDrivers() {
        return driverRepository.findAll()
                .stream()
                .map(DriverMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDriver(Integer id) {
        driverRepository.deleteById(id);
    }
    public boolean isNotOwner(String email, Integer id) {
        return driverRepository.findById(id)
                .map(d -> !d.getEmail().equalsIgnoreCase(email))
                .orElse(true); // treat not found as "not owner"
    }
}
