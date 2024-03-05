package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.entity.Role;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.exception.CustomerNotFoundException;
import com.bogoliubova.training_service.exception.TeacherNotFoundException;
import com.bogoliubova.training_service.repository.CustomerRepository;
import com.bogoliubova.training_service.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
public class TeacherDetailsServiceImpl implements UserDetailsService {

    public final TeacherRepository teacherRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws TeacherNotFoundException {
        Teacher teacher = teacherRepository.findTeacherByTeachEmail(email);
        if (teacher == null) {
            throw new TeacherNotFoundException("Teacher with login '" + email + "' not found");
        }
        return withUsername(email)
                .username(teacher.getTeachEmail())
                .password(teacher.getTeachEmail())
                .authorities(getAuthorities(teacher.getRoleSet()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> collection) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : collection) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));

            role.getAuthoritySet().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName().name())));
        }
        return authorities;
    }
}

