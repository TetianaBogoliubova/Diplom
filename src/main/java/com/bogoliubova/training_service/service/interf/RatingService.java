package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Rating;

public interface RatingService {
    Rating getRatingById(String id);

    Rating createNewRating(Rating rating);
}
