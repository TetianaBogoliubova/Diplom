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

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new CustomerNotFoundException(ErrorMassage.M_CUSTOMER_NOT_FOUND));
    }

    @Override
    public Customer createNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer updateCustomer, String customerId) {
        try {
            UUID uuidCustomerId = UUID.fromString(customerId);
            Customer existingCustomer = customerRepository.findById(uuidCustomerId)
                    .orElseThrow(() -> new CustomerNotFoundException(ErrorMassage.M_CUSTOMER_NOT_FOUND));

            existingCustomer.setFirstName(updateCustomer.getFirstName());
            existingCustomer.setLastName(updateCustomer.getLastName());
            existingCustomer.setCusEmail(updateCustomer.getCusEmail());
            existingCustomer.setLocation(updateCustomer.getLocation());
            existingCustomer.setDirections(updateCustomer.getDirections());

            return customerRepository.save(existingCustomer);
        } catch (Exception e) {
            throw new CustomerUpdateException(ErrorMassage.M_CUSTOMER_NOT_UPDATE);
        }
    }

    @Override
    public Customer patchUpdateCustomerById(String customerId, Map<String, Object> updates) {
        try {
            UUID uuidCustomerId = UUID.fromString(customerId);
            Customer existingCustomer = customerRepository.findById(uuidCustomerId)
                    .orElseThrow(() -> new CustomerNotFoundException(ErrorMassage.M_CUSTOMER_NOT_FOUND));

            applyUpdates(existingCustomer, updates);

            return customerRepository.save(existingCustomer);
        } catch (Exception e) {
            throw new CustomerUpdateException(ErrorMassage.M_CUSTOMER_NOT_UPDATE);
        }
    }

    private void applyUpdates(Customer customer, Map<String, Object> updates) {

        if (updates.containsKey("firstName")) {
            customer.setFirstName((String) updates.get("firstName"));
        }
        if (updates.containsKey("lastName")) {
            customer.setLastName((String) updates.get("lastName"));
        }
        if (updates.containsKey("cusEmail")) {
            customer.setCusEmail((String) updates.get("cusEmail"));
        }
    }

    @Override
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
    public CustomerDto getCLDId(String id) {
        Optional<CustomerDto> entity = customerRepository.findById(UUID.fromString(id))
                .map(c -> customerMapper.toDto(c));
        return entity.orElseThrow(() -> new CustomerNotFoundException(ErrorMassage.M_CUSTOMER_NOT_FOUND));
    }
}
