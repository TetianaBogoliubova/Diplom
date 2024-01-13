package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Rating;
import com.bogoliubova.training_service.repository.RatingRepository;
import com.bogoliubova.training_service.service.interf.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating getRatingById(String id) {

        return ratingRepository.findRatingByRatingId(UUID.fromString(id));
    }
}
