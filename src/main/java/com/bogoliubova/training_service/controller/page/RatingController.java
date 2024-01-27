package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Rating;

import com.bogoliubova.training_service.service.interf.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

//    @GetMapping("id_rating/{rating_id}")
//    public Rating getRatingByRatingId(@PathVariable("rating_id") String id) {
//        return ratingService.getRatingById(id);
//    }

    @PostMapping("/createRating")
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createNewRating(rating);
    }
}
