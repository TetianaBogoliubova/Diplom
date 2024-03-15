package com.bogoliubova.training_service.securityConfig;

import com.bogoliubova.training_service.security.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity//
@RequiredArgsConstructor
public class SecurityConfig2 {

    private final AuthTokenFilter authTokenFilter;
    private final AuthenticationProvider authenticationProvider;


    private static final String[] SWAGGER_LIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/swagger-ui/",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/configuration/**",
            "/configuration/ui",
            "/configuration/security",
            "/public",
            "/favicon.ico",
            "/h2-console/**",
            "/conferenc/v1/swagger-ui.html",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/",
            "/login", "/logout",
            "/csrf"
    };

    private static final String[] API = {
            "/book/**",
            "/customer/**",
            "/teacher/**"
    };

    @Bean
    //это единственный вариант, при котором появляется окно регисрации, но swagger и никакие запросы не отрабатывают
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/swagger*/**", "/configuration/**", "/v2/api-docs", "/webjars/**").permitAll()
                        .requestMatchers(SWAGGER_LIST).permitAll()
                        .requestMatchers("/customer/id_customer/**", "/customer/id_customerRest/**").hasRole("USER")
                        .requestMatchers("/teacher/id_teacher/**", "/teacher/id_teacherRest/**", "/teacher/getTeacherCity/**", "/teacher/getTeacherRating/**", "/teacher/getTeacherDirAndRating/**").hasRole("USER")
                        .requestMatchers("/customer/createCustomer", "/customer/updateCustomer/**", "/customer/part_updateCustomer/**").hasRole("USER")
                        .requestMatchers("/customer/deleteCustomer/**").hasRole("ADMIN")
                        .requestMatchers("/teacher/createTeacherRest", "/teacher/createTeacher").hasRole("USER"))
                .formLogin(Customizer.withDefaults())
                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"));
                //с добавление sessionManagement не вознакает даже окна авторизации
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.authenticationProvider(authenticationProvider)
                //.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

// С таким вариантом не появляется даже окно регистрации
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(a -> a
//                        .requestMatchers("/authenticate", "/auth/login", "/auth/logout").permitAll()
//                        .requestMatchers(SWAGGER_LIST).permitAll()
//                        .requestMatchers(API).permitAll()
//                        .requestMatchers(HttpMethod.GET).permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
//                .sessionManagement(m -> m
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .authenticationProvider(authenticationProvider);
//        return http.build();
//    }
}


