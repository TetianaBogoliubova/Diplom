package com.bogoliubova.training_service.controller.rest;

import com.bogoliubova.training_service.dto.CustomerDto;
import com.bogoliubova.training_service.service.interf.CustomerService;
import com.bogoliubova.training_service.validation.annotation.UuidRestChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CustomerRestController")
public class CustomerRestController {

    private final CustomerService customerService;

    // поиск по id + специальная валидация на формат id + exception
    @GetMapping("/id_customerRest/{customer_id}")
    //http://localhost:8080/customer/id_customerRest/483e5800-e40a-2cd3-f678-617223078864 ++
    @Operation(summary = "Return the customer by id",
            description = "If the customer id exists in the database, all information on this client is displayed",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer with this id exists"),
                    @ApiResponse(responseCode = "404", description = "Customer with this id does not exists")
            },
            security = {
                    @SecurityRequirement(name = "")
            },
            hidden = false
    )
    public CustomerDto getCustomerAndLocationAndDirection(@UuidRestChecker @PathVariable("customer_id") String id) {
        UUID customerId = UUID.fromString(id);
        return customerService.getCLDId(String.valueOf(customerId));
    }
}