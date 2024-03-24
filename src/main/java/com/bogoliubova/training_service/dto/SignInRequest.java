package com.bogoliubova.training_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {

    @NotBlank(message = "The login cannot be blank")
    private String login;

    @NotBlank(message = "The password cannot be blank")
    private String password;
}
