package com.bogoliubova.training_service.dto;

import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class TeacherDto {

    private String firstName;

    private String lastName;

    private String teachEmail;

    private List<Direction> directions;

    private Location location;

    private List<Rating> ratings;




    public TeacherDto(String firstName, String lastName, String teachEmail, List<Direction> directions, Location location, List<Rating> ratings) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teachEmail = teachEmail;
        this.directions = directions;
        this.location = location;
        this.ratings = ratings;
    }
}
