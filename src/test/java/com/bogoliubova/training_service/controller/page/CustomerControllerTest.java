package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.repository.CustomerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/createTestDB.sql")
@Sql("/addTest.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void getCustomerByCustomerIdIntegrationTest() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(UUID.fromString("33333333-e33a-3cd3-f333-333333333333"));
        customer.setFirstName("Albert");
        customer.setLastName("Wisoky");
        customer.setCusEmail("galen.crooks@hotmail.com");

        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/{customer_id}", "33333333-e33a-3cd3-f333-333333333333")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andReturn();

        assertEquals(200, mockPositiveResult.getResponse().getStatus());

        MvcResult mockNegativeResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/{customer_id}", "nonexistent_id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andReturn();

        assertEquals(404, mockNegativeResult.getResponse().getStatus());
        Customer actual = objectMapper.readValue(mockPositiveResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(actual, customer);
        assertEquals(actual.getFirstName(), customer.getFirstName());
        assertEquals(actual.getLastName(), customer.getLastName());
        assertEquals(actual.getCusEmail(), customer.getCusEmail());

    }

    @Test
    void createCustomerIntegrationTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setCusEmail("email@com");

        String newStringCustomer = objectMapper.writeValueAsString(customer);
        MvcResult createCustomerResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/customer/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();

        assertEquals(200, createCustomerResult.getResponse().getStatus());

        String customerResultJSON = createCustomerResult.getResponse().getContentAsString();
        Customer customerResult = objectMapper.readValue(customerResultJSON, Customer.class);

        assertEquals(customer.getFirstName(), customerResult.getFirstName());
        assertEquals(customer.getLastName(), customerResult.getLastName());
        assertEquals(customer.getCusEmail(), customerResult.getCusEmail());
    }

    @Test
    void updateCustomerByIdIntegrationTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Albert");
        customer.setLastName("Wisoky");
        customer.setCusEmail("galen.crooks@hotmail.com");

        String newStringCustomer = objectMapper.writeValueAsString(customer);
        MvcResult updateCustomerResult = mockMvc.perform(MockMvcRequestBuilders.put("/customer/updateCustomer/33333333-e33a-3cd3-f333-333333333333")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();

        assertEquals(200, updateCustomerResult.getResponse().getStatus());

        updateCustomerResult = mockMvc.perform(MockMvcRequestBuilders.get("/customer/id_customer/33333333-e33a-3cd3-f333-333333333333")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
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

//    @Test
//    void patchUpdateCustomerById() {
//    }

//    @Test
//    void deleteCustomerByBookId() {
//    }
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