package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.repository.CustomerRepository;
import com.bogoliubova.training_service.service.interf.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findCustomerByCustomerId(UUID.fromString(id));
    }

    @Override
    public Customer createNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}