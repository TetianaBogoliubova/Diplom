package com.bogoliubova.training_service.validation.constraint;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class RatingAnnotationRestCheckerTest {

    private final RatingAnnotationRestChecker ratingChecker = new RatingAnnotationRestChecker();

    private ConstraintValidatorContext getContext() {
        return mock(ConstraintValidatorContext.class);
    }

    @Test
    public void isValidPositiveTest() {
        assertTrue(ratingChecker.isValid(5, getContext()));
    }

    @Test
    public void isValidNegativeTest() {
        assertFalse(ratingChecker.isValid(0, getContext()));
        assertFalse(ratingChecker.isValid(12, getContext()));
        assertFalse(ratingChecker.isValid(null, getContext()));
    }
}