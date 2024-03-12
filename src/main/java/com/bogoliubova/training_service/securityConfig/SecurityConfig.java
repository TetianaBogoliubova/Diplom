package com.bogoliubova.training_service.securityConfig;

import com.bogoliubova.training_service.security.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity()
public class SecurityConfig {

    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http  //Вариант 1. как на первом уроке
                .authorizeHttpRequests(a -> a
         .requestMatchers("/", "/login", "/logout", "/swagger*/**",  "/configuration/**", "/v2/api-docs",  "/webjars/**").permitAll()
                        .requestMatchers("/customer/id_customer/**", "/customer/id_customerRest/**").hasRole("USER")
                        .requestMatchers("/teacher/id_teacher/**", "/teacher/id_teacherRest/**", "/teacher/getTeacherCity/**", "/teacher/getTeacherRating/**", "/teacher/getTeacherDirAndRating/**").hasRole("USER")
                        .requestMatchers("/customer/createCustomer", "/customer/updateCustomer/**", "/customer/part_updateCustomer/**").hasRole("USER")
                        .requestMatchers("/customer/deleteCustomer/**").hasRole("ADMIN")
                        .requestMatchers("/teacher/createTeacherRest", "/teacher/createTeacher").hasRole("USER"))
                .formLogin(Customizer.withDefaults())
                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
                .build();
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








    //////////////////////////////////
//        try {
//            return http.httpBasic().disable()
//                    .csrf(AbstractHttpConfigurer::disable).disable()
//                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    .authorizeHttpRequests(m -> m
//                                    .requestMatchers(
//                                            "/auth/login",
//                                            "/auth/new-token",
//                                            "/auth/refresh",
//                                            "/swagger-ui.html",
//                                            "/swagger-ui/**",
//                                            "/v2/api-docs/**"
//                                    )
//                                    .permitAll()
//                                    .anyRequest().authenticated()
//                                    .and()
//                                    .addFilterAfter(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                    )
//                    .build();
//        } catch (Exception e) {
//            throw new UnauthorizedException(e.getMessage());
//        }
//    }
//        return http
//                .csrf(CsrfConfigurer::disable)
//                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();

    }








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

