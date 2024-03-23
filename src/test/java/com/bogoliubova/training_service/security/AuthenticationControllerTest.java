package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private UserRepository userRepository;


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

//    @Test
//    @WithMockUser
//    void testRegistrationOfUser() throws Exception {
//        String requestBody = "{\"login\":\"testUser\", \"password\":\"testPassword\"}";
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").exists());
//    }

//
//    @Test
//    @WithMockUser
//    public void testAuthenticationOfUser() throws Exception {
//        String requestBody = "{\"username\":\"testUser\", \"password\":\"testPassword\"}";
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-in")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").exists());
//    }
//}


//    @Test
//    public void testRegistrationOfUser1() throws Exception {
//        SignUpRequest signUpRequest = new SignUpRequest("testUser", "testPassword");
//        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("testToken");
//
//        // Stubbing the service method
//        given(authenticationService.registrationUser(any(SignUpRequest.class))).willReturn(jwtAuthenticationResponse);
//
//        mockMvc.perform(post("/auth/sign-up")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(signUpRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").value("testToken"));
//    }
//}
//
//    @Test
//    public void testAuthenticationOfUser1() throws Exception {
//        SignInRequest signInRequest = new SignInRequest("testUser", "testPassword");
//        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("testToken");
//
//        // Stubbing the service method
//        given(authenticationService.authenticationUser(any(SignInRequest.class))).willReturn(jwtAuthenticationResponse);
//
//        mockMvc.perform(post("/auth/sign-in")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(signInRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").value("testToken"));
//    }
