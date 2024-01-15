package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Customer;

public interface CustomerService {
    Customer getCustomerById(String id);

    //Customer createNewCustomer(Customer customer);
}
