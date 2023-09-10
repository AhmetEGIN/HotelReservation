package com.egin.hotelreservation.common.annotations.validations.notPasteDateValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotPasteDateValidator.class)
public @interface NotPasteDate {

    String message() default "Es kann kein Datum eingegeben werden, das vor dem heutigen Tag liegt";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
