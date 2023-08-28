package com.egin.hotelreservation.common.annotations.validations.identityNumberValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IdentityNumberValidator.class)
@Documented
public @interface IdentityNumber {

    String message() default "Die ID-Nummer muss 11-stellig sein und die letzte Ziffer muss gerade sein.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
