package com.ione.service;

import com.ione.dto.CustomerRequestDTO;
import com.ione.dto.CustomerResponseDTO;
import com.ione.entity.Customer;
import com.ione.mapper.CustomerMapper;
import com.ione.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setCompanyName(dto.getCompanyName());
        customer.setCompanyAddress(dto.getCompanyAddress());
        customer.setCreatedAt(dto.getCreatedAt());
        customer.setContactPerson(dto.getContactPerson());
        String hashedPassword = passwordEncoder.encode(dto.getPasswordHash());
        customer.setPasswordHash(hashedPassword);
        customer.setBalance(dto.getBalance());
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDTO getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void updateBalance(Integer customerId, BigDecimal amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        BigDecimal newBalance = customer.getBalance().add(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient balance. Cannot go below 0.");
        }
        customer.setBalance(newBalance);
        customerRepository.save(customer);
    }
    @Override
    @Transactional
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
    public boolean isNotOwner(String email, Integer id) {
        return customerRepository.findById(id)
                .map(c -> !c.getEmail().equalsIgnoreCase(email))
                .orElse(true); // not found = not owner
    }
}
