package com.bogoliubova.training_service.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(a ->
//                        a.requestMatchers("/book/id_book/{book_id}").hasRole("BOOK")
//                                .requestMatchers("/book/id_book/{book_id}").hasRole("BOOKADMIN"))
//                .formLogin(Customizer.withDefaults())
//                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/book"))
//                .build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("book")
//                .password("123")
//               // .password("{bcrypt}")
//                .roles("BOOK")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("bookadmin")
//                .password("1234")
//                .roles("BOOKADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(book, admin);
//    }
///////////////////////////////////////////////////////////////////
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/book/id_book/**").hasAnyRole("BOOK", "BOOKADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/book")
//                .permitAll();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails bookUser = User.builder()
//                .username("book")
//                .password(passwordEncoder().encode("123"))
//                .roles("BOOK")
//                .build();
//
//        UserDetails adminUser = User.builder()
//                .username("bookadmin")
//                .password(passwordEncoder().encode("1234"))
//                .roles("BOOKADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(bookUser, adminUser);
//    }
//
//
//
//}
