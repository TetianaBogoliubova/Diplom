package com.bogoliubova.training_service.dto;

import lombok.Value;

@Value
public class LocationDto {

    String locationId;
    String country;
    String city;
    String firstName;
    String lastName;
    String teachEmail;
}
