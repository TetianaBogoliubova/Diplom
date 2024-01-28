package com.bogoliubova.training_service.dto;

import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.entity.Rating;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class TeacherDto {

    String firstName;

    String lastName;

    String teachEmail;

    Location location;

    //List<Rating> ratings;
}
