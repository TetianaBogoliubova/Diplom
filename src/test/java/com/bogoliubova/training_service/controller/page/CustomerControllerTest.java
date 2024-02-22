package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.repository.CustomerRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/createTestDB.sql")
@Sql("/addTestDB.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void createCustomerTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setCusEmail("email@com");

        String newCustomer = objectMapper.writeValueAsString(customer);
        MvcResult createCustomerResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCustomer))
                .andReturn();

        String customerResultJSON = createCustomerResult.getResponse().getContentAsString();
        Customer customerResult = objectMapper.readValue(customerResultJSON, Customer.class);

        assertEquals(200, createCustomerResult.getResponse().getStatus());
        assertEquals(customer.getFirstName(), customerResult.getFirstName());
        customerRepository.deleteAll();
    }

//    @Test
//    void createCustomer() {
//    }
//
//    @Test
//    void updateCustomerById() {
//    }

//    @Test
//    void patchUpdateCustomerById() {
//    }

//    @Test
//    void deleteCustomerByBookId() {
//    }
}