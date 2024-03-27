package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.entity.Direction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/createTestDB.sql")
@Sql("/addTestDB.sql")
class CustomerControllerTest {

    Customer customer = new Customer();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        List<Direction> directions = new ArrayList<>();

        customer.setCustomerId(UUID.fromString("483e5800-e40a-2cd3-f678-617223078864"));
        customer.setFirstName("Ismael");
        customer.setLastName("Spencer");
        customer.setCusEmail("kassandra.hammes@yahoo.com");
        customer.setDirections(directions);
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void getCustomerByCustomerIdPositiveTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());
        Customer customerResult = objectMapper.readValue(mockPositiveResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(customerResult, customer);
        assertEquals(customerResult.getFirstName(), customer.getFirstName());
        assertEquals(customerResult.getLastName(), customer.getLastName());
        assertEquals(customerResult.getCusEmail(), customer.getCusEmail());
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void getCustomerByCustomerIdNegativeTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockNegativeResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/{customer_id}", "nonexistent_id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andReturn();
        assertEquals(404, mockNegativeResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "admin", password = "111", roles = "ADMIN")
    void getCustomerByCustomerIdForbiddenTest() throws Exception {
        MvcResult mockForbiddenResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(403, mockForbiddenResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void createCustomerPositiveTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setCusEmail("email@com");

        String newStringCustomer = objectMapper.writeValueAsString(customer);
        MvcResult createCustomerPositiveResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/createCustomer")
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

    @Test
    @WithMockUser(username = "admin", password = "111", roles = "ADMIN")
    void createCustomerForbiddenTest() throws Exception {
        MvcResult mockForbiddenResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(403, mockForbiddenResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void updateCustomerByIdPositiveTest() throws Exception {
        customer.setFirstName("New Firstname");
        customer.setLastName("New Lastname");
        customer.setCusEmail("New cusEmail@com");
        String newStringCustomer = objectMapper.writeValueAsString(customer);

        MvcResult updateCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/updateCustomer/483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();
        assertEquals(200, updateCustomerResult.getResponse().getStatus());

        MvcResult getAfterUpdateResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Customer expectedCustomer = objectMapper.readValue(getAfterUpdateResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals("New Firstname", expectedCustomer.getFirstName());
        assertEquals("New Lastname", expectedCustomer.getLastName());
        assertEquals("New cusEmail@com", expectedCustomer.getCusEmail());
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void updateCustomerByIdNegativeTest() throws Exception {

        MvcResult updateNotExistingCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/updateCustomer/NotExistingCustomer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(400, updateNotExistingCustomerResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "admin", password = "111", roles = "ADMIN")
    void updateCustomerByIdForbiddenTest() throws Exception {
        MvcResult mockForbiddenResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/updateCustomer/483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(403, mockForbiddenResult.getResponse().getStatus());
    }


    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void patchUpdateCustomerByIdPositiveTest() throws Exception {
        customer.setFirstName("New Firstname");
        customer.setLastName("New Lastname");
        customer.setCusEmail("New cusEmail@com");
        String newStringCustomer = objectMapper.writeValueAsString(customer);

        MvcResult partUpdateCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .patch("/customer/part_updateCustomer/483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();
        assertEquals(200, partUpdateCustomerResult.getResponse().getStatus());

        MvcResult getAfterUpdateResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/id_customer/483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Customer expectedCustomer = objectMapper.readValue(getAfterUpdateResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals("New Firstname", expectedCustomer.getFirstName());
        assertEquals("New Lastname", expectedCustomer.getLastName());
        assertEquals("New cusEmail@com", expectedCustomer.getCusEmail());
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void patchUpdateCustomerByIdNegativeTest() throws Exception {
        MvcResult patchNotExistingCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .patch("/customer/part_updateCustomer/NotExistingCustomer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(400, patchNotExistingCustomerResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "admin", password = "111", roles = "ADMIN")
    void patchUpdateCustomerByIdForbiddenTest() throws Exception {
        MvcResult mockForbiddenResult = mockMvc.perform(MockMvcRequestBuilders
                        .patch("/customer/part_updateCustomer/483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(403, mockForbiddenResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "admin", password = "222", roles = "ADMIN")
    void deleteCustomerByBookIdPositiveTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/deleteCustomer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "admin", password = "222", roles = "ADMIN")
    void deleteCustomerByCustomerIdNegativeTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockNegativeResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/deleteCustomer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andReturn();
        assertEquals(404, mockNegativeResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void deleteCustomerByCustomerIdForbiddenTest() throws Exception {
        MvcResult mockForbiddenResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/deleteCustomer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078864")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(403, mockForbiddenResult.getResponse().getStatus());
    }
}
