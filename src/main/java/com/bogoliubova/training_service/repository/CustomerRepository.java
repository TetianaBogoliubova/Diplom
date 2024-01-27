package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findCustomerByCustomerId(UUID customerId);

    @Modifying
    @Query("UPDATE Customer c SET c.firstName = :firstName, c.lastName = :lastName, c.cusEmail = :cusEmail, c.location = :location, c.directions = :directions WHERE c.customerId = :customerId")
    int patchUpdateCustomer(@Param("customerId") String customerId,
                            @Param("firstName") String firstName,
                            @Param("lastName") String lastName,
                            @Param("cusEmail") String cusEmail,
                            @Param("location") Location location,
                            @Param("directions") Direction directions);

}
