package com.bogoliubova.training_service.service.interf;
import com.bogoliubova.training_service.dto.CustomerDto;
import com.bogoliubova.training_service.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CustomerService {
    Customer getCustomerById(String id);

    Customer createNewCustomer(Customer customer);

    Customer updateCustomer(Customer existingCustomer, String id);

    ResponseEntity<String> deleteCustomerById(String customerId);

    Customer patchUpdateCustomerById(String customerId, Map<String, Object> updates);

    CustomerDto getCLDId(String id);
}