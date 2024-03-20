package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.dto.SignInRequest;
import com.bogoliubova.training_service.dto.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

   // @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse registrationOfUser(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.registrationUser(request);
    }

   // @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse authenticationOfUser(@RequestBody @Valid SignInRequest request) {
        return authenticationService.authenticationUser(request);
    }
}
