package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.entity.Role;
import com.bogoliubova.training_service.entity.User;
import com.bogoliubova.training_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with login '" + login + "' not found");
        }
        System.out.println("********************** user found " + user);
        return withUsername(login)
                .username(user.getLogin())
                .password(user.getPassword())
                .authorities(getAuthorities(user.getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> collection) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : collection) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));

            role.getAuthoritySet().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName().name()))
            );
        }
        return authorities;
    }
}
