package com.bogoliubova.training_service.dto;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Value
//@Data
public class ServicesDto {
    String type;

    BigDecimal servicePrice;
}
