package com.bogoliubova.training_service.securityConfig;


import com.bogoliubova.training_service.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder pas = new BCryptPasswordEncoder();
        System.out.println("****************" + pas);
        return pas;//new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        System.out.println("**********************" + provider);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(a -> a
                        //.requestMatchers("/", "/login", "/logout").permitAll()
                        .requestMatchers("/customer/id_customer/**", "/customer/id_customerRest/**").hasRole("USER")
                        .requestMatchers("/customer/createCustomer", "/customer/updateCustomer/**", "/customer/part_updateCustomer/**").hasRole("PARTNER")
                        .requestMatchers("/customer/deleteCustomer/**").hasRole("ADMIN")
                        .requestMatchers("/teacher/id_teacherRest/**", "/teacher/getTeacherCity/**", "/teacher/getTeacherRating/**", "/teacher/getTeacherDirAndRating/**").hasRole("USER")
                        .requestMatchers("/teacher/createTeacherRest", "/teacher/id_teacher/**", "/teacher/getTeacherRating/**", "/teacher/getTeacherDirAndRating/**", "/teacher/createTeacher").hasRole("PARTNER")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
                .build();
    }


//    @Autowired
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    private CustomerDetailsServiceImpl customerDetailsService;
//
//    @Autowired
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    private TeacherDetailsServiceImpl teacherDetailsService;
//

//
////    public BCryptPasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//    @Bean
//    public DaoAuthenticationProvider customerAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customerDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

//    @Bean
//    public DaoAuthenticationProvider teacherAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(teacherDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(a -> a
//                        // .requestMatchers("/", "/login", "/logout").permitAll()
//                        .requestMatchers("/customer/id_customer/**", "/customer/id_customerRest/**").hasRole("USER")
//                        .requestMatchers("/customer/createCustomer", "/customer/updateCustomer/**", "/customer/part_updateCustomer/**").hasRole("PARTNER")
//                        .requestMatchers("/customer/deleteCustomer/**").hasRole("ADMIN")
//                        // .requestMatchers("/teacher/id_teacherRest/**", "/teacher/getTeacherCity/**", "/teacher/getTeacherRating/**", "/teacher/getTeacherDirAndRating/**").hasRole("USER")
//                        // .requestMatchers("/teacher/createTeacherRest", "/teacher/id_teacher/**", "/teacher/getTeacherRating/**", "/teacher/getTeacherDirAndRating/**", "/teacher/createTeacher").hasRole("PARTNER")
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
//                .build();
//    }


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
}


//                        .requestMatchers("/customer/id_customer/{customer_id}").hasRole("USER")
//                        .requestMatchers("/customer/id_customerRest/{customer_id}").hasRole("USER")
//                        .requestMatchers("/customer/createCustomer").hasRole("PARTNER")
//                        .requestMatchers("/customer/updateCustomer/{customer_id}").hasRole("PARTNER")
//                        .requestMatchers("/customer/part_updateCustomer/{customer_id}").hasRole("PARTNER")
//                        .requestMatchers("/customer/deleteCustomer/{customer_id}").hasRole("ADMIN")
//
//                        .requestMatchers("/teacher/id_teacherRest/{teacher_id}").hasRole("USER")
//                        .requestMatchers("/teacher/getTeacherCity/{city}").hasRole("USER")
//                        .requestMatchers("/teacher/getTeacherRating/{rating}").hasRole("USER")
//                        .requestMatchers("/teacher/getTeacherDirAndRating/{direction}/{rating}").hasRole("USER")
//                        .requestMatchers("/teacher/createTeacherRest").hasRole("PARTNER")
//                        .requestMatchers("/teacher/id_teacher/{teacher_id}").hasRole("USER")
//                        .requestMatchers("/teacher/getTeacherRating/{rating}").hasRole("USER")
//                        .requestMatchers("/teacher/getTeacherDirAndRating/{direction}/{rating}").hasRole("USER")
//                        .requestMatchers("/teacher/createTeacher").hasRole("PARTNER")
//