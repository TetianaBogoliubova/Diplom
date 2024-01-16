package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findCustomerByCustomerId(UUID customerId);
}
