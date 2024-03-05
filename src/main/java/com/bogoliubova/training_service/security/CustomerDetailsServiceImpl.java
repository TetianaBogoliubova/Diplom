package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.entity.Role;
import com.bogoliubova.training_service.exception.CustomerNotFoundException;
import com.bogoliubova.training_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
@Component
public class CustomerDetailsServiceImpl implements UserDetailsService {

    public final CustomerRepository customerRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws CustomerNotFoundException {
        Customer customer = customerRepository.findCustomerByCusEmail(email);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with login '" + email + "' not found");
        }
        return withUsername(email)
                .username(customer.getCusEmail())
                .password(customer.getCusEmail())
                .authorities(getAuthorities(customer.getRoleSet()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> collection) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : collection) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));

            role.getAuthoritySet().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName().name())));
        }
        return authorities;
    }
}
