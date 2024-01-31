package com.bogoliubova.training_service.validation.annotation;

import com.bogoliubova.training_service.validation.constraint.UuidAnnotationChecker1;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UuidAnnotationChecker1.class})

public @interface UuidChecker1 {
    String message() default "It's not UUID format!!!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
