package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.service.interf.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/id_customer/{customer_id}")
    public Customer getCustomerByCustomerId(@PathVariable("customer_id") String id) {
        return customerService.getCustomerById(id);
    }

//    @PostMapping("/createCustomer")
//    public Customer createCustomer(@RequestBody Customer customer) {
//        return customerService.createNewCustomer(customer);
//    }
}
