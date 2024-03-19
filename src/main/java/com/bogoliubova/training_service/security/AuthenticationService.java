package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.dto.AllRoles;
import com.bogoliubova.training_service.dto.SignInRequest;
import com.bogoliubova.training_service.dto.SignUpRequest;
import com.bogoliubova.training_service.entity.User;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // Регистрация пользователя

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(AllRoles.ROLE_USER);

        userService.create(user);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);

//        User user = User.builder()
//                .login(request.getLogin())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(AllRoles.ROLE_USER)
//                .build();
//
//        userService.create(user);
//
//        String jwt = jwtService.generateToken(user);
//        return new JwtAuthenticationResponse(jwt);
    }

    // Аутентификация пользователя
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getLogin());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}