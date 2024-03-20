package com.bogoliubova.training_service.dto;

import com.bogoliubova.training_service.entity.Rating;
import lombok.Data;

import java.util.List;

@Data
public class TeacherFullNameAndRatingDto {

    private String firstName;

    private String lastName;

    private List<Rating> ratings;
}
