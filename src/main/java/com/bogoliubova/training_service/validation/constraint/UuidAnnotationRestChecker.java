package com.bogoliubova.training_service.validation.constraint;

import com.bogoliubova.training_service.validation.annotation.UuidRestChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UuidAnnotationRestChecker implements ConstraintValidator<UuidRestChecker, String> {

    private static final String TEMPLATE = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .filter(uuid -> !uuid.isBlank())
                .map(uuid -> uuid.matches(TEMPLATE))
                .orElse(false);
    }
}



