package com.bogoliubova.training_service.securityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http  //Вариант 1. как на первом уроке
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/customer/id_customer/**", "/customer/id_customerRest/**").hasRole("USER")
                        .requestMatchers("/teacher/id_teacher/**", "/teacher/id_teacherRest/**", "/teacher/getTeacherCity/**", "/teacher/getTeacherRating/**", "/teacher/getTeacherDirAndRating/**").hasRole("USER")
                        .requestMatchers("/customer/createCustomer", "/customer/updateCustomer/**", "/customer/part_updateCustomer/**").hasRole("USER")
                        .requestMatchers("/customer/deleteCustomer/**").hasRole("ADMIN")
                        .requestMatchers("/teacher/createTeacherRest", "/teacher/createTeacher").hasRole("USER"))
                .formLogin(Customizer.withDefaults())
                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
                .build();
    }
}
// Вариант 2 - не появляется даже окно авторизации
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(
//                                new AntPathRequestMatcher("/customer/id_customer/**"),
//                                new AntPathRequestMatcher("/customer/id_customerRest/**"),
//                                new AntPathRequestMatcher("/customer/createCustomer"),
//                                new AntPathRequestMatcher("/customer/updateCustomer/**"),
//                                new AntPathRequestMatcher("/customer/part_updateCustomer/**"),
//                                new AntPathRequestMatcher("/customer/deleteCustomer/**"),
//                                new AntPathRequestMatcher("/teacher/id_teacher/**"),
//                                new AntPathRequestMatcher("/teacher/createTeacher"),
//                                new AntPathRequestMatcher("/teacher/id_teacherRest/**"),
//                                new AntPathRequestMatcher("/teacher/createTeacherRest"),
//                                new AntPathRequestMatcher("/teacher/getTeacherCity/**"),
//                                new AntPathRequestMatcher("/teacher/getTeacherRating/**"),
//                                new AntPathRequestMatcher("/teacher/getTeacherDirAndRating/**")
//                        )
//                        .hasRole("USER")
//                        // .requestMatchers(String.valueOf(HttpMethod.OPTIONS), "/**").permitAll()///////
//                        .anyRequest()//.authenticated())
//                        .permitAll())
//                .formLogin(Customizer.withDefaults())
//                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
//
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//}

//Создание юзеров в памяти, если не хотим вводить их в БД
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .passwordEncoder(rawPassword -> new BCryptPasswordEncoder().encode(rawPassword))
//                .username("user")
//                .password("user")//{bcrypt}
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .passwordEncoder(new BCryptPasswordEncoder()::encode)
//                .username("admin")
//                .password("admin")//{bcrypt}
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

