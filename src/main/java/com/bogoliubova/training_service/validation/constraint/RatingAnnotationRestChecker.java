package com.bogoliubova.training_service.validation.constraint;

import com.bogoliubova.training_service.validation.annotation.RatingRestChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class RatingAnnotationRestChecker implements ConstraintValidator<RatingRestChecker, Integer> {

    @Override
    public boolean isValid(Integer rating, ConstraintValidatorContext context) {
        return Optional.ofNullable(rating)
                .map(r -> r > 0 && r <= 10)
                .orElse(false);
    }
}
