package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.exception.CustomerNotFoundException;
import com.bogoliubova.training_service.repository.CustomerRepository;
import com.bogoliubova.training_service.securityConfig.SecurityConfig2;
import com.bogoliubova.training_service.service.interf.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Import(SecurityConfig2.class)//
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/createTestDB.sql")
@Sql("/addTestDB.sql")
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Customer customer = new Customer();
    CustomerService customerService;
    CustomerController customerController;
    CustomerRepository customerRepository;

    private ConstraintValidatorContext getContext() {
        return mock(ConstraintValidatorContext.class);
    }

    @BeforeEach
    public void setup() {
        List<Direction> directions = new ArrayList<>();
        Location location = null;
        customer.setCustomerId(UUID.fromString("483e5800-e40a-2cd3-f678-617223078864"));
        customer.setFirstName("Ismael");
        customer.setLastName("Spencer");
        customer.setCusEmail("kassandra.hammes@yahoo.com");
        customer.setDirections(directions);
        customer.setLocation(location);
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void getCustomerByCustomerIdPositiveTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockPositiveResult = mockMvc.perform(get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078864")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());

        mockMvc.perform(get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078864")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void getCustomerByCustomerIdNegTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
//        MvcResult mockPositiveResult = mockMvc.perform(get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078000")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(customerNewString))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertEquals(404, mockPositiveResult.getResponse().getStatus());

        mockMvc.perform(get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078000")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString));
               // .andExpect(status().isOk());
        assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomerByCustomerId("483e5800-e40a-2cd3-f678-617223078000"));
    }












    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void getCustomerNeg() throws Exception {
        mockMvc.perform(get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078800"))
                .andExpect(status().isUnauthorized());
    }












    @ParameterizedTest
    @WithMockUser
    @CsvSource({
            // "483e5800-e40a-2cd3-f678-617223078864, true",
            "483e5800-e40a-2cd3-f678-617223078000, false",
            "false-uuid-format, false",
            "null, false",
            "' ', false"
    })
    void getCustomerByCustomerIdNegativeTest1(String input) throws Exception {
        mockMvc.perform(get("/customer/id_customer/"+input)
                       // .param("id", input)
                        .with(csrf()))
                .andExpect(status().isBadRequest());
        //int status = result.getResponse().getStatus();
        // assertEquals(expected, customerController.getCustomerByCustomerId(input, getContext());
        //assertEquals(404, status);
//        if (!expected) {
//            Customer customer = new CustomerControllercustomerController.getCustomerByCustomerId(input, getContext());
//            assertNull(customer); // Проверяем, что возвращенный клиент пустой или null
//        }

    }


    @Test
    @WithMockUser//(username = "user", password = "111", roles = "USER")
    void getCustomerByCustomerIdNegativeTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
        mockMvc.perform(get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078000")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON));
        //.content(customerNewString))
        //.andExpect(status().isNotFound())
        //.andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomerNotFoundException));

        assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomerByCustomerId("483e5800-e40a-2cd3-f678-617223078000"));
    }


    @Test
    @WithMockUser(username = "user", password = "111", roles = "USER")
    void getCustomerByCustomerId403Test() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockNegativeResult = mockMvc.perform(get("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078000")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @WithMockUser(username = "user", password = "111", roles = "USER")
    @Test
    void createCustomerIntegrationTest() throws Exception {
        Customer expectedCustomer = new Customer();
        expectedCustomer.setFirstName("New Firstname");
        expectedCustomer.setLastName("New Lastname");
        expectedCustomer.setCusEmail("New cusEmail@com");
        String newStringCustomer = objectMapper.writeValueAsString(expectedCustomer);
        MvcResult createCustomerPositiveResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/createCustomer")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();

        assertEquals(200, createCustomerPositiveResult.getResponse().getStatus());
        assertEquals("New Firstname", expectedCustomer.getFirstName());
        assertEquals("New Lastname", expectedCustomer.getLastName());
        assertEquals("New cusEmail@com", expectedCustomer.getCusEmail());
    }

    @WithMockUser(username = "user", password = "111", roles = "USER")
    @Test
    void updateCustomerByIdPositiveTest() throws Exception {
        String newStringCustomer = objectMapper.writeValueAsString(customer);

        MvcResult updateCustomerResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/updateCustomer/483e5800-e40a-2cd3-f678-617223078864")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();
        assertEquals(200, updateCustomerResult.getResponse().getStatus());

        Customer expectedCustomer = new Customer();
        expectedCustomer.setFirstName("New Firstname");
        expectedCustomer.setLastName("New Lastname");
        expectedCustomer.setCusEmail("New cusEmail@com");

        assertNotEquals(expectedCustomer.getFirstName(), customer.getFirstName());
        assertNotEquals(expectedCustomer.getLastName(), customer.getLastName());
        assertNotEquals(expectedCustomer.getCusEmail(), customer.getCusEmail());
    }

    @WithMockUser(username = "user", password = "111", roles = "USER")
    @Test
    void updateCustomerByIdNegativeTest() throws Exception {
        // String newStringCustomer = objectMapper.writeValueAsString(customer);

        Customer updateCustomer = new Customer();
        updateCustomer.setFirstName("New Firstname");
        updateCustomer.setLastName("New Lastname");
        updateCustomer.setCusEmail("New cusEmail@com");
        // Преобразование клиента в JSON строку
        String newStringCustomer = objectMapper.writeValueAsString(updateCustomer);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/updateCustomer/483e5800-e40a-2cd3-f678-617223078000")
                        //.param("id", "customerId")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomerNotFoundException));
    }


    @WithMockUser(username = "user", password = "111", roles = "USER")
    @Test
    void patchUpdateCustomerByIdPositiveTest() throws Exception {
        String newStringCustomer = objectMapper.writeValueAsString(customer);

        MvcResult patchCustomerResult = mockMvc.perform(MockMvcRequestBuilders.patch("/customer/part_updateCustomer/483e5800-e40a-2cd3-f678-617223078864")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newStringCustomer))
                .andReturn();
        assertEquals(200, patchCustomerResult.getResponse().getStatus());

        Customer expectedCustomer = new Customer();
        expectedCustomer.setFirstName("Albert");
        expectedCustomer.setLastName("Wisoky");
        expectedCustomer.setCusEmail("New cusEmail@com");

        assertNotEquals(expectedCustomer.getFirstName(), customer.getFirstName());
        assertNotEquals(expectedCustomer.getLastName(), customer.getLastName());
        assertNotEquals(expectedCustomer.getCusEmail(), customer.getCusEmail());
    }

    @WithMockUser//(username = "admin", password = "333", roles = "ADMIN")
    @Test
    void deleteCustomerByIdPositiveTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);
        MvcResult mockPositiveResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078864")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                // .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, mockPositiveResult.getResponse().getStatus());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/id_customer/{customer_id}", "483e5800-e40a-2cd3-f678-617223078864")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerNewString))
                .andExpect(status().isOk());
    }


    @WithMockUser(username = "admin", password = "333", roles = "ADMIN")
    @Test
    void deleteCustomerByIdIntegrationTest() throws Exception {
        String customerNewString = objectMapper.writeValueAsString(customer);

        MvcResult getBeforeDeleteResult = mockMvc.perform(get("/customer/id_customer/614e5310-e75a-9cd6-f593-566726876254")
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

    @ParameterizedTest
    @WithMockUser(username = "admin", password = "333", roles = "ADMIN")
    @CsvSource({
            "483e5800-e40a-2cd3-f678-617223078864, true",
            "483e5800-e40a-2cd3-f678-617223078000, false",
            "false-uuid-format, false",
            "null, false",
            "' ', false"
    })
    void delCustomerByCustomerIdNegativeTest1(String input) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/deleteCustomer/{customer_id}", input)
                        .param("id", input)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void cannotDeleteCustomerIfNotAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/customer/deleteCustomer/{customer_id}", "96453")
                        .with(csrf())
                        .with(anonymous())
                )
                .andExpect(status().isUnauthorized());
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
