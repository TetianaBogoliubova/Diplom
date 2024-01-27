package com.bogoliubova.training_service.dto;

import lombok.Data;
import lombok.Value;

@Value
//@Data
public class ServicesDto {
    String type;
    Double servicePrice;
}
