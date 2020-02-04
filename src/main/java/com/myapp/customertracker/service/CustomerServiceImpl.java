package com.myapp.customertracker.service;

import com.myapp.customertracker.entity.Customer;
import com.myapp.customertracker.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getCustomers() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return repository.findById(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        if(customer.getId() != null) {
            Optional<Customer> customerOptional = repository.findById(customer.getId());
            if (customerOptional.isPresent()) {
                Customer foundCustomer = customerOptional.get();
                foundCustomer.setFirstName(customer.getFirstName());
                foundCustomer.setLastName(customer.getLastName());
                foundCustomer.setEmail(customer.getEmail());
                customer = foundCustomer;
            }
        }
        repository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
