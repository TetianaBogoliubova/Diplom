package com.bogoliubova.training_service.controller.rest;

import com.bogoliubova.training_service.dto.CustomerDto;
import com.bogoliubova.training_service.service.interf.CustomerService;
import com.bogoliubova.training_service.validation.annotation.UuidChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/id_customerRest/{customer_id}")
    public CustomerDto getCustomerAndLocationAndDirection(@UuidChecker @PathVariable("customer_id") UUID id) {
        return customerService.getCLDId(id);
    }

//    @GetMapping("/id_customerRest1/{customer_id}")
//    public CustomerDto getCustomerAndLocationAndDirection1(@UuidChecker @PathVariable("customer_id") String id) {
//        return customerService.getCLDId(id);
//    }

}
