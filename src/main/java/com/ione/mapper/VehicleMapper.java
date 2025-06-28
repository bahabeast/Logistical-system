package com.ione.mapper;
import com.ione.dto.VehicleRequestDTO;
import com.ione.dto.VehicleResponseDTO;
import com.ione.entity.Vehicle;
import com.ione.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public static Vehicle toEntity(VehicleRequestDTO dto, Driver driver) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicencePlate());
        vehicle.setModel(dto.getModel());
        vehicle.setType(dto.getVehicleType());
        vehicle.setYearOfManufacture(dto.getYearOfManufacture());
        vehicle.setCapacityTons(dto.getCapacityTons());
        vehicle.setVolumeCubicMeters(dto.getVolumeCubicMeters());
        vehicle.setVin(dto.getVin());
        vehicle.setIsFree(dto.getIsFree());
        vehicle.setInsuranceValidUntil(dto.getInsuranceValidUntil());
        vehicle.setRegistrationCertificate(dto.getRegistrationCertificate());
        vehicle.setOwner(driver);
        vehicle.setCreatedAt(dto.getCreatedAt());
        return vehicle;
    }

    public static VehicleResponseDTO toDTO(Vehicle vehicle) {
        VehicleResponseDTO dto = new VehicleResponseDTO();
        dto.setId(vehicle.getId());
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setModel(vehicle.getModel());
        dto.setType(vehicle.getType());
        dto.setYearOfManufacture(vehicle.getYearOfManufacture());
        dto.setCapacityTons(vehicle.getCapacityTons());
        dto.setVolumeCubicMeters(vehicle.getVolumeCubicMeters());
        dto.setVin(vehicle.getVin());
        dto.setIsFree(vehicle.getIsFree());
        dto.setInsuranceValidUntil(vehicle.getInsuranceValidUntil());
        dto.setRegistrationCertificate(vehicle.getRegistrationCertificate());
        dto.setFullName(vehicle.getOwner().getFullName());
        dto.setCreatedAt(vehicle.getCreatedAt());
        dto.setDriverId(vehicle.getOwner().getId());
        return dto;
    }

}
