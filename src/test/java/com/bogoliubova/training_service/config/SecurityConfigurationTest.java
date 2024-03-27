package com.bogoliubova.training_service.config;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.logging.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
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

    @Autowired
    private WebApplicationContext context;

    //@Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters((jakarta.servlet.Filter) springSecurityFilterChain)
                .build();
    }

        @Test
    void testSecurityFilterChain33() throws Exception {
        mockMvc.perform(get("/swagger-ui.html"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/swagger-ui/index.html"));
    }
    @Test
    void testSecurityFilterChain() throws Exception {
        mockMvc
          .perform(MockMvcRequestBuilders.post("/").with(csrf()));
    }

    @Test
    void testSecurityFilterChain21() throws Exception {
        mockMvc
        .perform(MockMvcRequestBuilders.post("/").with(csrf().asHeader()));
    }

    @Test
    void testSecurityFilterChain2() throws Exception {
        mockMvc
        .perform(MockMvcRequestBuilders.post("/").with(csrf().useInvalidToken()));

    }

    @Test
    void testSecurityFilterChain23() throws Exception {
        mockMvc
                .perform(get("/").with(user("user")));

    }

    @Test
    void testSecurityFilterChain24() throws Exception {
        mockMvc
                .perform(get("/user").with(user("user").password("111").roles("USER", "ADMIN")));
    }

    @Test
    void testSecurityFilterChain5() throws Exception {
        mockMvc.perform(get("/").with(user("UserDet")));

    }

    @Test
    void testSecurityFilterChain252() throws Exception {
       //  mockMvc  .perform(get("/").with(authentication("authentication")));

    }

    @Test
    void testSecurityFilterChain52() throws Exception {
        // mockMvc.perform(get("/").with(securityContext("securityContext")));
    }

    @Test
    void testSecurityFilterChain25223() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .defaultRequest(get("/").with(user("user").roles("USER")))
                .addFilters((jakarta.servlet.Filter) springSecurityFilterChain)
                .build();
    }

    @Test
    void testSecurityFilterChain15023() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                //.addFilters(springSecurityFilterChain)
                .defaultRequest(get("/").with(testSecurityContext()))
                .build();

    }

    @Test
    void testSecurityFilterChain125221() throws Exception {
        mockMvc.perform(get("/").with(httpBasic("user", "password")));

    }

    @Test
    void testSecurityFilterChain1523() throws Exception {
        mockMvc.perform(formLogin());

    }

    @Test
    void testSecurityFilterChain251221() throws Exception {
        mockMvc.perform(formLogin("/auth").user("user").password("111"));
    }

    @Test
    void testSecurityFilterChain2512210() throws Exception {
        mockMvc.perform(logout());

    }

    @Test
    void testSecurityFilterChain2512821() throws Exception {
        mockMvc.perform(logout("/signout"));

    }

    @Test
    void testSecurityFilterChain25122618() throws Exception {
        mockMvc.perform(formLogin().password("invalid"))
                .andExpect(unauthenticated());

    }

    @Test
    void testSecurityFilterChain28512621() throws Exception {
        mockMvc.perform(formLogin())
                .andExpect(authenticated());

    }

    @Test
    void testSecurityFilterChain82512261() throws Exception {
        mockMvc.perform(formLogin().user("user"))
                .andExpect(authenticated().withRoles("USER", "ADMIN"));

    }

    @Test
    void testSecurityFilterChain28512216() throws Exception {
        mockMvc.perform(formLogin().user("user"))
                .andExpect(authenticated().withUsername("user"));

    }

    @Test
    void testSecurityFilterChain86251221() throws Exception {
        // mockMvc

    }

    @Test
    void testSecurityFilterChain2851221() throws Exception {
        // mockMvc

    }

    @Test
    void testSecurityFilterChain8251221() throws Exception {
        // mockMvc

    }

}
