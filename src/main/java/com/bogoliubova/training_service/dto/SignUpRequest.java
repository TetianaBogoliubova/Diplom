package com.bogoliubova.training_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String login;

    @NotBlank
    private String password;
}
