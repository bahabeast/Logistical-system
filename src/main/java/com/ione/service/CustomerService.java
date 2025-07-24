package com.ione.service;

import com.ione.dto.CustomerRequestDTO;
import com.ione.dto.CustomerResponseDTO;
import jakarta.validation.Valid;


import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO customer);
    CustomerResponseDTO getCustomerById(Integer id);
    List<CustomerResponseDTO> getAllCustomers();
    void deleteCustomer(Integer id);
    void updateBalance(Integer customerId, BigDecimal amount);
    boolean isNotOwner(String email, Integer id);
}

