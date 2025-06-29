package com.ione.mapper;

import com.ione.dto.OrderRequestDTO;
import com.ione.dto.OrderResponseDTO;
import com.ione.entity.Customer;
import com.ione.entity.Order;
import com.ione.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public static Order toEntity(OrderRequestDTO dto, Customer customer, Vehicle vehicle) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setCargoDescription(dto.getCargoDescription());
        order.setWeightTons(dto.getWeightTons());
        order.setVolumeCubicMeters(dto.getVolumeCubicMeters());
        order.setPickupLocation(dto.getPickupLocation());
        order.setDeliveryLocation(dto.getDeliveryLocation());
        order.setVehicle(vehicle);
        order.setRecipientPhone(dto.getRecipientPhone());
        order.setVehicleType(dto.getVehicleType());
        order.setDeliveryStatus(dto.getDeliveryStatus());
        return order;
    }

    public static OrderResponseDTO toDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerCompanyName(order.getCustomer().getCompanyName());
        dto.setCargoDescription(order.getCargoDescription());
        dto.setWeightTons(order.getWeightTons());
        dto.setVolumeCubicMeters(order.getVolumeCubicMeters());
        dto.setPickupLocation(order.getPickupLocation());
        dto.setDeliveryLocation(order.getDeliveryLocation());
        dto.setRecipientPhone(order.getRecipientPhone());
        dto.setDeliveryStatus(order.getDeliveryStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setVehicleType(order.getVehicle().getVehicleType());
        return dto;
    }
}
