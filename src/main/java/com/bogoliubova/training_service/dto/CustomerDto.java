package com.bogoliubova.training_service.dto;

import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Location;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    private String customerId;

    private String cusEmail;

    private Location location;

    private List<Direction> directions;
}
