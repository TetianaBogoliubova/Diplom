package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.service.interf.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/id_customer/{customer_id}")
//http://localhost:8080/customer/id_customer/483e5800-e40a-2cd3-f678-617223078864
    public Customer getCustomerByCustomerId(@PathVariable("customer_id") String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/createCustomer")//http://localhost:8080/customer/createCustomer
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createNewCustomer(customer);
    }

    @PutMapping(value = "/updateCustomer/{customer_id}")
//http://localhost:8080/customer/updateCustomer/483e5800-e40a-2cd3-f678-617223078864
    public Customer updateCustomerById(@RequestBody Customer updateCustomer, @PathVariable("customer_id") String id) {
        return customerService.updateCustomer(updateCustomer, id);
    }

    @PatchMapping("/part_updateCustomer/{customer_id}")
    public Customer patchUpdateCustomerById(@PathVariable("customer_id") String customerId,
                                            @RequestBody Map<String, Object> updates) {
        return customerService.patchUpdateCustomerById(customerId, updates);
    }

    @DeleteMapping("/deleteCustomer/{customer_id}")
    public ResponseEntity<String> deleteCustomerByBookId(@PathVariable("customer_id") String customerId) {
        return customerService.deleteCustomerById(customerId);
    }

}
