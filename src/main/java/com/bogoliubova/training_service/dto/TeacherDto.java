package com.bogoliubova.training_service.dto;

import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.entity.Rating;
import lombok.Data;

import java.util.List;

@Data

public class TeacherDto {

    private String firstName;

    private String lastName;

    private String teachEmail;

    private List<Direction> directions;

    private Location location;

    private List<Rating> ratings;

}
