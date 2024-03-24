package com.bogoliubova.training_service.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    @WithMockUser
    void registrationUserPositiveTest() throws Exception {
        String requestBody = "{\"login\":\"testUser\", \"password\":\"password\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    void registrationUserWithoutLoginTest() throws Exception {
        String requestBody = "{\"login\": \", \"password\":\"password\" }";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    void registrationUserWithoutPasswordTest() throws Exception {
        String requestBody = "{\"login\":\"testUser\", \"password\": }";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    void authenticationUserPositiveTest() throws Exception {
        String requestBody = "{\"login\":\"testUser\", \"password\":\"password\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    void authenticationUserWithoutLoginTTest() throws Exception {
        String requestBody = "{\"login\": \", \"password\":\"password\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser
    void authenticationUserWithoutPasswordTTest() throws Exception {
        String requestBody = "{\"login\": testUser\", \"password\": \"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }
}
