package com.bogoliubova.training_service.controller.page;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/createTestDB.xml")
@Sql("/addTestDB")
class CustomerControllerTest {

    @Test
    void getCustomerByCustomerId() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomerById() {
    }

    @Test
    void patchUpdateCustomerById() {
    }

    @Test
    void deleteCustomerByBookId() {
    }
}