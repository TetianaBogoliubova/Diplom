package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.dto.CustomerDto;
import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.exception.CustomerNotFoundException;
import com.bogoliubova.training_service.exception.CustomerUpdateException;
import com.bogoliubova.training_service.exception.ErrorMassage;
import com.bogoliubova.training_service.mapper.CustomerMapper;
import com.bogoliubova.training_service.repository.CustomerRepository;
import com.bogoliubova.training_service.service.interf.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Customer getCustomerById(String id) {
        return customerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new CustomerNotFoundException(ErrorMassage.M_CUSTOMER_NOT_FOUND));
    }

    @Override
    @Transactional
    public Customer createNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer updateCustomer, String customerId) {

        UUID uuidCustomerId = UUID.fromString(customerId);
        Customer existingCustomer = customerRepository.findById(uuidCustomerId)
                .orElseThrow(() -> new CustomerNotFoundException(ErrorMassage.M_CUSTOMER_NOT_FOUND));

        existingCustomer.setFirstName(updateCustomer.getFirstName());
        existingCustomer.setLastName(updateCustomer.getLastName());
        existingCustomer.setCusEmail(updateCustomer.getCusEmail());
        existingCustomer.setLocation(updateCustomer.getLocation());
        existingCustomer.setDirections(updateCustomer.getDirections());

        return customerRepository.save(existingCustomer);
    }

    @Override
    @Transactional(isolation = READ_COMMITTED)
    public Customer patchUpdateCustomerById(String customerId, Map<String, Object> updates) {

        UUID uuidCustomerId = UUID.fromString(customerId);
        Customer existingCustomer = customerRepository.findById(uuidCustomerId)
                .orElseThrow(() -> new CustomerNotFoundException(ErrorMassage.M_CUSTOMER_NOT_FOUND));

        applyUpdates(existingCustomer, updates);

        return customerRepository.save(existingCustomer);
    }

    private void applyUpdates(Customer customer, Map<String, Object> updates) {

        if (updates.containsKey("firstName")) {
            customer.setFirstName((String) updates.get("firstName"));
        } else throw new CustomerUpdateException(ErrorMassage.M_CUSTOMER_NOT_UPDATE);

        if (updates.containsKey("lastName")) {
            customer.setLastName((String) updates.get("lastName"));
        } else throw new CustomerUpdateException(ErrorMassage.M_CUSTOMER_NOT_UPDATE);

        if (updates.containsKey("cusEmail")) {
            customer.setCusEmail((String) updates.get("cusEmail"));
        } else throw new CustomerUpdateException(ErrorMassage.M_CUSTOMER_NOT_UPDATE);
    }


    @Override
    @Transactional(isolation = SERIALIZABLE)
    public ResponseEntity<String> deleteCustomerById(String customerId) {
        UUID uuidCustomerId = UUID.fromString(customerId);
        Optional<Customer> customer = customerRepository.findById(uuidCustomerId);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public CustomerDto getCLDId(String id) {
        Optional<CustomerDto> entity = customerRepository.findById(UUID.fromString(id))
                .map(customerMapper::toDto);
        return entity.orElseThrow(() -> new CustomerNotFoundException(ErrorMassage.M_CUSTOMER_NOT_FOUND));
    }
}