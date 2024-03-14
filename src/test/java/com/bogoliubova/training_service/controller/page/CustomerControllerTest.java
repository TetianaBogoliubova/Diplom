package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.TrainingServiceApplication;
import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.entity.Direction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TrainingServiceApplication.class)
@BootstrapWith(value = org.springframework.boot.test.context.SpringBootTestContextBootstrapper.class)

@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/createTestDB.sql")
@Sql("/addTestDB.sql")   //FKpexv2ec6otg5gu05u78rw1tt4
//@WebMvcTest(controllers = CustomerControllerTest.class,
//        excludeAutoConfiguration = {
//                UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class
//        })

class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void getCustomerByCustomerIdIntegrationTest() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(UUID.fromString("614e5310-e75a-9cd6-f593-566726876254"));
        customer.setFirstName("Albert");
        customer.setLastName("Wisoky");
        customer.setCusEmail("galen.crooks@hotmail.com");

        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/{customer_id}", "614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());

//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/customer/id_customer/{customer_id}", "614e5310-e75a-9cd6-f593-566726876254")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(customerNewString))
//                .andExpect(status().isOk())
//                .andExpect(content().json(customerNewString));

/////////////////////////
        Customer expectedCustomer = new Customer();
        expectedCustomer.setCustomerId(UUID.fromString("614e5310-e75a-9cd6-f593-566726876254"));
        expectedCustomer.setFirstName("Albert");
        expectedCustomer.setLastName("Wisoky");
        expectedCustomer.setCusEmail("galen.crooks@hotmail.com");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/{customer_id}", "614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(expectedCustomer.getCustomerId().toString()))
                .andExpect(jsonPath("$.firstName").value(expectedCustomer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(expectedCustomer.getLastName()))
                .andExpect(jsonPath("$.cusEmail").value(expectedCustomer.getCusEmail()));

/////////////////



//        MvcResult mockNegativeResult = mockMvc.perform(MockMvcRequestBuilders
//                        .get("/customer/id_customer/{customer_id}", "nonexistent_id")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(customerNewString))
//                .andReturn();
       // assertEquals(404, mockNegativeResult.getResponse().getStatus());
//        Customer customerResult = objectMapper.readValue(mockPositiveResult.getResponse().getContentAsString(), new TypeReference<>() {
//        });
//
//        assertEquals(customerResult, customer);
//        assertEquals(customerResult.getFirstName(), customer.getFirstName());
//        assertEquals(customerResult.getLastName(), customer.getLastName());
//        assertEquals(customerResult.getCusEmail(), customer.getCusEmail());
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void getCustomerByCustomerIdIntegrationTest1() throws Exception {
        Customer expectedCustomer = new Customer();
        expectedCustomer.setCustomerId(UUID.fromString("614e5310-e75a-9cd6-f593-566726876254"));
        expectedCustomer.setFirstName("Albert");
        expectedCustomer.setLastName("Wisoky");
        expectedCustomer.setCusEmail("galen.crooks@hotmail.com");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/{customer_id}", "614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Customer actualCustomer = objectMapper.readValue(responseBody, Customer.class);

        assertEquals(expectedCustomer.getCustomerId(), actualCustomer.getCustomerId());
        assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());
        assertEquals(expectedCustomer.getLastName(), actualCustomer.getLastName());
        assertEquals(expectedCustomer.getCusEmail(), actualCustomer.getCusEmail());
    }
    @WithMockUser(username = "partner", password = "222", roles = "PARTNER")
    @Test
    void createCustomerIntegrationTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setCusEmail("email@com");
        customer.setDirections(null);
        customer.setLocation(null);

        String newStringCustomer = objectMapper.writeValueAsString(customer);
        MvcResult createCustomerPositiveResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/createCustomer")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();

         assertEquals(200, createCustomerPositiveResult.getResponse().getStatus());

        String customerResultJSON = createCustomerPositiveResult.getResponse().getContentAsString();
        Customer customerResult = objectMapper.readValue(customerResultJSON, Customer.class);

        assertEquals(customer.getFirstName(), customerResult.getFirstName());
        assertEquals(customer.getLastName(), customerResult.getLastName());
        assertEquals(customer.getCusEmail(), customerResult.getCusEmail());
        assertNotNull(customerResult.getCustomerId());//уникальность id
    }
    @WithMockUser(username = "partner", password = "222", roles = "PARTNER")
    @Test
    void updateCustomerByIdIntegrationTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Albert");
        customer.setLastName("Wisoky");
        customer.setCusEmail("galen.crooks@hotmail.com");

        String newStringCustomer = objectMapper.writeValueAsString(customer);

        MvcResult getBeforeUpdateResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        //assertEquals(200, getBeforeUpdateResult.getResponse().getStatus());

        MvcResult updateNotExistingCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/updateCustomer/NotExistingCustomer")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        //assertEquals(400, updateNotExistingCustomerResult.getResponse().getStatus());

        MvcResult updateCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/updateCustomer/614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();
        assertEquals(200, updateCustomerResult.getResponse().getStatus());

        Customer customerStringResult = objectMapper.readValue(updateCustomerResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        Customer expectedCustomer = new Customer();
        expectedCustomer.setFirstName("New Firstname");
        expectedCustomer.setLastName("New Lastname");
        expectedCustomer.setCusEmail("New cusEmail@com");

        assertNotEquals(expectedCustomer.getFirstName(), customerStringResult.getFirstName());
        assertNotEquals(expectedCustomer.getLastName(), customerStringResult.getLastName());
        assertNotEquals(expectedCustomer.getCusEmail(), customerStringResult.getCusEmail());
    }

    @WithMockUser(username = "partner", password = "222", roles = "PARTNER")
    @Test
    void patchUpdateCustomerByIdIntegrationTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Albert");
        customer.setLastName("Wisoky");
        customer.setCusEmail("galen.crooks@hotmail.com");

        String newStringCustomer = objectMapper.writeValueAsString(customer);

        MvcResult getBeforePatchResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
       // assertEquals(200, getBeforePatchResult.getResponse().getStatus());

        MvcResult patchNotExistingCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .patch("/customer/part_updateCustomer/NotExistingCustomer")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(400, patchNotExistingCustomerResult.getResponse().getStatus());

        MvcResult patchCustomerResult = mockMvc.perform(MockMvcRequestBuilders.patch("/customer/part_updateCustomer/614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();
        assertEquals(200, patchCustomerResult.getResponse().getStatus());
        Customer customerStringResult = objectMapper.readValue(patchCustomerResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        Customer expectedCustomer = new Customer();
        expectedCustomer.setFirstName("Albert");
        expectedCustomer.setLastName("Wisoky");
        expectedCustomer.setCusEmail("New cusEmail@com");

        assertEquals(expectedCustomer.getFirstName(), customerStringResult.getFirstName());
        assertEquals(expectedCustomer.getLastName(), customerStringResult.getLastName());
        assertNotEquals(expectedCustomer.getCusEmail(), customerStringResult.getCusEmail());
    }

    @WithMockUser(username = "admin", password = "333", roles = "ADMIN")
    @Test
    void deleteCustomerByBookIdIntegrationTest() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(UUID.fromString("614e5310-e75a-9cd6-f593-566726876254"));
        customer.setFirstName("Albert");
        customer.setLastName("Wisoky");
        customer.setCusEmail("galen.crooks@hotmail.com");

        String customerNewString = objectMapper.writeValueAsString(customer);

        MvcResult getBeforeDeleteResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andReturn();
        assertEquals(200, getBeforeDeleteResult.getResponse().getStatus());

        MvcResult deleteNotExistingCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/deleteCustomer/{customer_id}", "614e5310-e75a-9cd6-f593-566726870000")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(404, deleteNotExistingCustomerResult.getResponse().getStatus());

        MvcResult deleteCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/deleteCustomer/{customer_id}", "614e5310-e75a-9cd6-f593-566726876254")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andReturn();
        assertEquals(200, deleteCustomerResult.getResponse().getStatus());
    }
}

//    @Test
//        //второй вариант такого же теста
//    void getCustomerByCustomerIdIntegrationTest2() throws Exception {
//
//        String customerId = "33333333-e33a-3cd3-f333-333333333333";
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customer/id_customer/{customer_id}", customerId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String responseJson = result.getResponse().getContentAsString();
//        Customer customer = new ObjectMapper().readValue(responseJson, Customer.class);
//
//        assertEquals("Albert", customer.getFirstName());
//        assertEquals("Wisoky", customer.getLastName());
//        assertEquals("galen.crooks@hotmail.com", customer.getCusEmail());
//    }


//    MvcResult mockBadRequestResult = mockMvc.perform(MockMvcRequestBuilders
//                    .get("/customer/id_customer/{customer_id}", "invalid_id_format")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(customerNewString))
//            .andReturn();
//
//    assertEquals(400, mockBadRequestResult.getResponse().getStatus());


//проверка на создание такого же объекта для метода create - проверка на ошибку, если ввести такие же данные, какие уже существуют
//    @Test
//    void createExistingCustomerIntegrationTest() throws Exception {
//        // Создание клиента с определенными данными
//        Customer customer = new Customer();
//        customer.setFirstName("Existing");
//        customer.setLastName("Customer");
//        customer.setCusEmail("existing@com");
//
//        String newStringCustomer = objectMapper.writeValueAsString(customer);
//        MvcResult createCustomerPositiveResult = mockMvc
//                .perform(MockMvcRequestBuilders.post("/customer/createCustomer")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(newStringCustomer))
//                .andReturn();
//
//        assertEquals(200, createCustomerPositiveResult.getResponse().getStatus());
//
//        // Повторная попытка создания клиента с теми же данными
//        MvcResult createExistingCustomerResult = mockMvc
//                .perform(MockMvcRequestBuilders.post("/customer/createCustomer")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(newStringCustomer))
//                .andReturn();
//
//        // Ожидается код ответа 400 (Bad Request), так как клиент с такими данными уже существует
//        assertEquals(400, createExistingCustomerResult.getResponse().getStatus());


//////////////////////
//@DeleteMapping("/deleteCustomer/{customer_id}")
//@Operation(summary = "Delete customer by id",
//        description = "Deletes a customer based on the provided id.",
//        responses = {
//                @ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
//                @ApiResponse(responseCode = "400", description = "Invalid id format"),
//                @ApiResponse(responseCode = "404", description = "Customer with this id does not exist")
//        },
//        security = {@SecurityRequirement(name = "")},
//        hidden = false
//)
//public ResponseEntity<String> deleteCustomerByBookId(@PathVariable("customer_id") String customerId) {
//    try {
//        UUID uuidCustomerId = UUID.fromString(customerId);
//        // ваш код удаления
//        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
//    } catch (IllegalArgumentException ex) {
//        // Неверный формат id
//        return new ResponseEntity<>("Invalid id format", HttpStatus.BAD_REQUEST);
//    }
//}
