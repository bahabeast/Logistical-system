package com.ione.service;

import com.ione.entity.Customer;
import com.ione.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer buildCustomer(Integer id, String email) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCompanyName("LogiTech Inc.");
        customer.setContactPerson("Alice Smith");
        customer.setEmail(email);
        customer.setPasswordHash("securePass123");
        customer.setPhoneNumber("87001234567");
        customer.setCompanyAddress("Astana, Block 5");
        customer.setBalance(BigDecimal.ZERO);
        return customer;
    }

    @Test
    void isNotOwner_ShouldReturnFalse_WhenEmailMatches() {
        Customer customer = buildCustomer(1, "customer@example.com");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        boolean result = customerService.isNotOwner("customer@example.com", 1);
        assertFalse(result);
    }

    @Test
    void isNotOwner_ShouldReturnTrue_WhenEmailDoesNotMatch() {
        Customer customer = buildCustomer(1, "other@example.com");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        boolean result = customerService.isNotOwner("customer@example.com", 1);
        assertTrue(result);
    }

    @Test
    void isNotOwner_ShouldReturnTrue_WhenCustomerNotFound() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        boolean result = customerService.isNotOwner("customer@example.com", 1);
        assertTrue(result);
    }
}
