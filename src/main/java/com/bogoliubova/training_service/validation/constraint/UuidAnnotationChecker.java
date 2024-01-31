package com.bogoliubova.training_service.validation.constraint;

import com.bogoliubova.training_service.validation.annotation.UuidChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UuidAnnotationChecker implements ConstraintValidator<UuidChecker, UUID> {

    private static final String TEMPLATE = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";


    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .filter(uuid -> !uuid.toString().isBlank())
                .map(uuid -> uuid.toString().matches(TEMPLATE))
                .orElse(false);
    }
}
