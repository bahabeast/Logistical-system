package com.ione.service;

import com.ione.dto.VehicleRequestDTO;
import com.ione.dto.VehicleResponseDTO;
import com.ione.entity.Driver;
import com.ione.entity.Vehicle;
import com.ione.entity.enums.Role;
import com.ione.mapper.VehicleMapper;
import com.ione.repository.DriverRepository;
import com.ione.repository.VehicleRepository;
import com.ione.security.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    @Transactional
    public VehicleResponseDTO createVehicle(VehicleRequestDTO dto) {
        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        Vehicle vehicle = VehicleMapper.toEntity(dto, driver);
        Vehicle saved = vehicleRepository.save(vehicle);
        return VehicleMapper.toDTO(saved);
    }

    @Override
    public VehicleResponseDTO getVehicleById(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return VehicleMapper.toDTO(vehicle);
    }

    @Override
    public List<VehicleResponseDTO> getAllVehicles() {
        Role role = AuthUtil.getCurrentUserRole();

        if (role == Role.CUSTOMER) {
            // Customers only see FREE vehicles
            return vehicleRepository.findAllByIsFreeTrue()
                    .stream()
                    .map(VehicleMapper::toDTO)
                    .collect(Collectors.toList());
        }

        return vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<VehicleResponseDTO> getFreeVehicles() {
        return vehicleRepository.findAllByIsFreeTrue()
                .stream()
                .map(VehicleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteVehicle(Integer id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
