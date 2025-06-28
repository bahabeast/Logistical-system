package com.ione.mapper;

import com.ione.dto.CustomerRequestDTO;
import com.ione.dto.CustomerResponseDTO;
import com.ione.entity.Customer;
import org.springframework.stereotype.Component;
@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setCompanyName(dto.getCompanyName());
        customer.setCompanyAddress(dto.getCompanyAddress());
        customer.setCreatedAt(dto.getCreatedAt());
        customer.setContactPerson(dto.getContactPerson());
        return customer;
    }
    public CustomerResponseDTO toDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setCompanyName(customer.getCompanyName());
        dto.setCompanyAddress(customer.getCompanyAddress());
        dto.setCreatedAt(customer.getCreatedAt());
        dto.setContactPerson(customer.getContactPerson());
        return dto;
    }
}
