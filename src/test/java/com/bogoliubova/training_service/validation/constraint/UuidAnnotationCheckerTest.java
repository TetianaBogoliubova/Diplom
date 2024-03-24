package com.bogoliubova.training_service.validation.constraint;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UuidAnnotationCheckerTest {

    private final UuidAnnotationChecker uuidAnnotationChecker = new UuidAnnotationChecker();

    private ConstraintValidatorContext getContext() {
        return mock(ConstraintValidatorContext.class);
    }

    @Test
    public void isValidPositiveTest() {
        assertTrue(uuidAnnotationChecker.isValid("837e8317-e35a-4cd1-f710-387841923887", getContext()));
    }

    @Test
    public void isValidNegativeTest() {
        assertFalse(uuidAnnotationChecker.isValid("false-uuid-format", getContext()));
        assertFalse(uuidAnnotationChecker.isValid(null, getContext()));
        assertFalse(uuidAnnotationChecker.isValid(" ", getContext()));
    }
}