package com.bogoliubova.training_service.config;

import com.bogoliubova.training_service.security.JwtAuthenticationFilter;
import com.bogoliubova.training_service.security.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter = mock(JwtAuthenticationFilter.class);

    @Mock
    private UserService userService = mock(UserService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    void testAuthenticationProviderBean() {
        SecurityConfiguration securityConfiguration = new SecurityConfiguration(jwtAuthenticationFilter, userService);
        AuthenticationProvider authenticationProvider = securityConfiguration.authenticationProvider();
        assertNotNull(authenticationProvider);
        assertTrue(authenticationProvider instanceof DaoAuthenticationProvider);
    }
    @Test
    void testAuthenticationManager() {
        assertNotNull(authenticationManager);
    }

    //при обращении к пути /swagger-ui.html выполняется перенаправление на URL /swagger-ui/index.html.
    @Test
    void securityFilterChainCheckURLFofSwaggerTest() throws Exception {
        mockMvc.perform(get("/swagger-ui.html"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/swagger-ui/index.html"));
    }

    @Test
    void securityFilterChainGetWithOutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isNotFound());
    }

    @Test
    void securityFilterChainPostWithOutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/"))
                .andExpect(status().isNotFound());
    }

    @Test
    void securityFilterChainPutWithOutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.put("/").with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void securityFilterChainPatchWithOutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/"))
                .andExpect(status().isNotFound());
    }

    //проверка обработки POST-запроса на корневой путь с CSRF-защитой и что CSRF-токен правильно добавлен в заголовок запроса.
    @Test
    void securityFilterChainCheckProtectionFromCSRFWithHeader() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/").with(csrf().asHeader()))
                .andExpect(status().isNotFound());
    }

    //проверка обработки POST-запроса на корневой путь с использованием недействительного токена CSRF.
    @Test
    void securityFilterChainCheckProtectionFromCSRFWithInvalidToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/").with(csrf().useInvalidToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSecurityFilterChain1523() throws Exception {
        mockMvc.perform(formLogin())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testSecurityFilterChain2512210() throws Exception {
        mockMvc.perform(logout())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testSecurityFilterChain2512821() throws Exception {
        mockMvc.perform(logout("/signout"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testSecurityFilterChain25128212() throws Exception {
        mockMvc.perform(logout("/signin"))
                .andExpect(status().is3xxRedirection());
    }
}
