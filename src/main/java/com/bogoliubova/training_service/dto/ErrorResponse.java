package com.bogoliubova.training_service.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;

    private String url;
}
