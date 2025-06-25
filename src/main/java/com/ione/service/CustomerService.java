package com.ione.service;

import com.ione.entity.Customer;


import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer getCustomerById(Integer id);
    List<Customer> getAllCustomers();
    void deleteCustomer(Integer id);
}

