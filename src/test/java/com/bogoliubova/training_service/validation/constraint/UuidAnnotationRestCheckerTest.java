package com.bogoliubova.training_service.validation.constraint;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UuidAnnotationRestCheckerTest {
    UuidAnnotationRestChecker uuidRestChecker = new UuidAnnotationRestChecker();

    private ConstraintValidatorContext getContext() {
        return mock(ConstraintValidatorContext.class);
    }

    @ParameterizedTest
    @CsvSource({
            "837e8317-e35a-4cd1-f710-387841923887, true",
            "false-uuid-format, false",
            "null, false",
            "' ', false"
    })
    void isValidTest(String input, boolean expected) {
        assertEquals(expected, uuidRestChecker.isValid(input, getContext()));
    }
}