package com.bogoliubova.training_service.validation.annotation;

import com.bogoliubova.training_service.validation.constraint.RatingAnnotationRestChecker;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingAnnotationRestChecker.class)
public @interface RatingRestChecker {

    String message() default "The number is not within the rating!!!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
