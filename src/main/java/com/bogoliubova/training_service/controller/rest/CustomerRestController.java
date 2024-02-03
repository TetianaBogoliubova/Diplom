package com.bogoliubova.training_service.controller.rest;

import com.bogoliubova.training_service.dto.CustomerDto;
import com.bogoliubova.training_service.service.interf.CustomerService;
import com.bogoliubova.training_service.validation.annotation.UuidChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Validated
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/id_customerRest/{customer_id}")
    //http://localhost:8080/customer/id_customerRest/483e5800-e40a-2cd3-f678-617223078864
    public CustomerDto getCustomerAndLocationAndDirection(@Valid @UuidChecker @PathVariable("customer_id") String id) {
        UUID customerId = UUID.fromString(id);
        return customerService.getCLDId(String.valueOf(customerId));
    }
}

