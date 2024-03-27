package com.bogoliubova.training_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicesDto {

    private String type;

    private BigDecimal servicePrice;
}
