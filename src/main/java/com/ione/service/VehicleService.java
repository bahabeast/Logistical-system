package com.ione.service;
import com.ione.dto.VehicleRequestDTO;
import com.ione.dto.VehicleResponseDTO;
import com.ione.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    VehicleResponseDTO createVehicle(VehicleRequestDTO dto);
    VehicleResponseDTO getVehicleById(Integer id);
    List<VehicleResponseDTO> getAllVehicles();
    List<VehicleResponseDTO> getFreeVehicles();
    void deleteVehicle(Integer id);
    Vehicle save(Vehicle vehicle);

}
