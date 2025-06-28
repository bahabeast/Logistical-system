package com.ione.service;

import com.ione.entity.Customer;
import com.ione.entity.Driver;
import com.ione.repository.CustomerRepository;
import com.ione.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Try to find a customer first
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return User.builder()
                    .username(customer.getEmail())
                    .password(customer.getPasswordHash())
                    .roles("CUSTOMER")
                    .build();
        }

        // Try to find a driver
        Driver driver = driverRepository.findByEmail(email);
        if (driver != null) {
            return User.builder()
                    .username(driver.getEmail())
                    .password(driver.getPasswordHash())
                    .roles("DRIVER")
                    .build();
        }

        throw new UsernameNotFoundException("No user found with email: " + email);
    }
}
