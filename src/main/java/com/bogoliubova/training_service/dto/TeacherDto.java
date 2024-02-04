package com.bogoliubova.training_service.dto;

import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.entity.Rating;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data

public class TeacherDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String teachEmail;

    private List<Direction> directions;

    private Location location;

    private List<Rating> ratings;

}
