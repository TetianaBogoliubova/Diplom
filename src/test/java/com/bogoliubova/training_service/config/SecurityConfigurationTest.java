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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    //проверяет конфигурацию аутентификационного провайдера
    @Test
    void authenticationProviderTest() {
        SecurityConfiguration securityConfiguration = new SecurityConfiguration(jwtAuthenticationFilter, userService);
        AuthenticationProvider authenticationProvider = securityConfiguration.authenticationProvider();
        assertNotNull(authenticationProvider);
        assertTrue(authenticationProvider instanceof DaoAuthenticationProvider);
    }

    @Test
    void authenticationManagerTest() {

        assertNotNull(authenticationManager);
    }

//Проверка метода securityFilterChain при отключенной защите CSRF - без передачи токена CSRF

    //при обращении к пути /swagger-ui.html выполняется перенаправление на URL /swagger-ui/index.html.
    @Test
    void securityFilterChainCheckURLFofSwaggerTest() throws Exception {
        mockMvc.perform(get("/swagger-ui.html"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/swagger-ui/index.html"));
    }

    //проверка GET-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainGetWithoutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isNotFound());
    }

    //проверка POSR-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainPostWithoutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/"))
                .andExpect(status().isNotFound());
    }

    //проверка PUT-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainPutWithoutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.put("/"))
                .andExpect(status().isNotFound());
    }

    //проверка PUTCH-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainPatchWithoutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/"))
                .andExpect(status().isNotFound());
    }

    //проверка DELETE-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainDeleteWithoutCsrfTokenTest() throws Exception {
        mockMvc
                .perform(delete("/"))
                .andExpect(status().isNotFound());
    }

    //Проверка метода securityFilterChain при включенной защите CSRF - требует передачи CSRF-токена

    //проверка GET-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainGetWithCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/").with(csrf()))
                .andExpect(status().isNotFound());
    }

    //проверка POSR-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainPostWithCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/").with(csrf()))
                .andExpect(status().isNotFound());
    }

    //проверка PUT-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainPutWithCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.put("/").with(csrf()))
                .andExpect(status().isNotFound());
    }

    //проверка PUTCH-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainPatchWithCsrfTokenTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/").with(csrf()))
                .andExpect(status().isNotFound());
    }

    //проверка DELETE-запроса без передачи CSRF-токена на корневой эндпоинт "/"
    @Test
    void securityFilterChainDeleteWithCsrfTokenTest() throws Exception {
        mockMvc
                .perform(delete("/").with(csrf()))
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

    //проверка механизма аутентификации и успешного перенаправления на другую страницу
    @Test
    void securityFilterChainAuthenticationAndRedirection() throws Exception {
        mockMvc.perform(formLogin())
                .andExpect(status().is3xxRedirection());
    }

    //проверка  механизма выхода из системы и успешного перенаправления на другую страницу
    @Test
    void testSecurityFilterChainLogoutAndRedirection() throws Exception {
        mockMvc.perform(logout())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void securityFilterChainAuthenticationAndRedirectionWithPath() throws Exception {
        mockMvc.perform(logout("/sign-out"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testSecurityFilterChainLogoutAndRedirectionWithPath() throws Exception {
        mockMvc.perform(logout("/sign-in"))
                .andExpect(status().is3xxRedirection());
    }
}
