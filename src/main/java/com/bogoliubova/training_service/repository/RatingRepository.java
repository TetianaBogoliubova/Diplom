package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
    Rating findRatingByRatingId(UUID ratingId);
}
