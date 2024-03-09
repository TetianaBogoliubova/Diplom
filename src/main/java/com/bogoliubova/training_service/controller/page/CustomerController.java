package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.service.interf.CustomerService;
import com.bogoliubova.training_service.validation.annotation.UuidChecker;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


//https://ru.stackoverflow.com/questions/1560853/spring-security-%D0%B2%D1%81%D0%B5-%D0%B2%D1%80%D0%B5%D0%BC%D1%8F-%D0%B2%D1%8B%D0%B4%D0%B0%D0%B5%D1%82-%D0%BE%D1%88%D0%B8%D0%B1%D0%BA%D1%83-403

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "CustomerController")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    // поиск по id + специальная валидация для проверки формата id + exception
    @GetMapping("/id_customer/{customer_id}")
    @CrossOrigin(origins = "*")
//http://localhost:8080/customer/id_customer/483e5800-e40a-2cd3-f678-617223078864  +++
    @Operation(summary = "Return the customer by id",
            description = "If the customer id exists in the database, all information on this client is displayed",
            tags = "Customer",
            externalDocs = @ExternalDocumentation(description = "All documents for method getCustomerByCustomerId",
                    url = "https://google.com/"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer with this id exists"),
                    @ApiResponse(responseCode = "404", description = "Customer with this id does not exists")
            },
            security = {
                    @SecurityRequirement(name = "")
            },
            hidden = false
    )
    public Customer getCustomerByCustomerId(@UuidChecker @PathVariable("customer_id") String id) {
        return customerService.getCustomerById(id);
    }


    // создание нового объекта
    @PostMapping("/createCustomer")//http://localhost:8080/customer/createCustomer
    @Operation(summary = "Create a new customer",
            description = "If necessary fields are filled in, a new customer is created",
            //tags = "Customer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Insert jason format data according to Customer Entity class",
                    required = true,
                    content = @Content(
                            mediaType = "applicaton/json",
                            schema = @Schema(implementation = Customer.class)
                    )
            )
    )
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createNewCustomer(customer);
    }

    // обновление нового объекта по id + exception
    @PutMapping(value = "/updateCustomer/{customer_id}")
//http://localhost:8080/customer/updateCustomer/483e5800-e40a-2cd3-f678-617223078864
    @Operation(summary = "Update customer in database by id",
            description = "Updates customer details based on the provided JSON format data.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "update jason format data according to Customer Entity class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)
                    )
            ),
            parameters = {
                    @Parameter(name = "customer_id", description = "ID of the customer to be updated", in = ParameterIn.PATH, schema = @Schema(type = "string", format = "uuid")),
                    @Parameter(name = "first_name", description = "First name of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "last_name", description = "Last name of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "cus_email", description = "Email of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "location", description = "Location of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "directions", description = "List of directions of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
            }
    )
    public Customer updateCustomerById(@RequestBody Customer updateCustomer, @PathVariable("customer_id") String id) {
        return customerService.updateCustomer(updateCustomer, id);
    }

    // частичное обновление объекта по id + exception (2 вида exception)
    @PatchMapping("/part_updateCustomer/{customer_id}")
    @Operation(summary = "Update customer in database by id",
            description = "",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "insert jason format data according to Customer Entity class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)
                    )
            ),
            parameters = {
                    @Parameter(name = "customer_id", description = "ID of the customer to be updated", in = ParameterIn.PATH, schema = @Schema(type = "string", format = "uuid")),
                    @Parameter(name = "first_name", description = "First name of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "last_name", description = "Last name of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "cus_email", description = "Email of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "location", description = "Location of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "directions", description = "List of directions of the customer to be updated", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
            }
    )
    public Customer patchUpdateCustomerById(@PathVariable("customer_id") String customerId,
                                            @RequestBody Map<String, Object> updates) {
        return customerService.patchUpdateCustomerById(customerId, updates);
    }

    // удаление объекта по id
    @DeleteMapping("/deleteCustomer/{customer_id}")
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
    public ResponseEntity<String> deleteCustomerByBookId(@PathVariable("customer_id") String customerId) {
        return customerService.deleteCustomerById(customerId);
    }
}