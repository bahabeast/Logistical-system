package com.ione.mapper;

import com.ione.dto.ConsignmentRequestDTO;
import com.ione.dto.ConsignmentResponseDTO;
import com.ione.entity.Consignment;
import com.ione.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class ConsignmentMapper {

    public static Consignment toEntity(ConsignmentRequestDTO dto, Order order) {
        Consignment consignment = new Consignment();
        consignment.setOrder(order);
        consignment.setTotalCost(dto.getTotalCost());
        consignment.setPlatformFee(dto.getPlatformFee());
        consignment.setDriverPayment(dto.getDriverPayment());
        consignment.setFileUrl(dto.getFileUrl());
        return consignment;
    }

    public static ConsignmentResponseDTO toDTO(Consignment consignment) {
        ConsignmentResponseDTO dto = new ConsignmentResponseDTO();
        dto.setId(consignment.getId());
        dto.setOrderId(consignment.getOrder().getId());
        dto.setTotalCost(consignment.getTotalCost());
        dto.setPlatformFee(consignment.getPlatformFee());
        dto.setDriverPayment(consignment.getDriverPayment());
        dto.setFileUrl(consignment.getFileUrl());
        dto.setIssuedAt(consignment.getIssuedAt());
        return dto;
    }
}
