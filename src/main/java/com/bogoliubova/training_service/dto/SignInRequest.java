package com.bogoliubova.training_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {

    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String login;


    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
