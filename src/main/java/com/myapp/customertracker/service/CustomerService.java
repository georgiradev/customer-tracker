package com.myapp.customertracker.service;

import com.myapp.customertracker.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getCustomers();

    Optional<Customer> getCustomer(Long id);

    void saveCustomer(Customer customer);

    void deleteCustomer(Long id);
}
