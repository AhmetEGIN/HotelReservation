package com.egin.hotelreservation.common.annotations.validations.identityNumberValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentityNumberValidator implements ConstraintValidator<IdentityNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.length() == 11 && ((int) value.charAt(value.length() - 1)) % 2 == 0;
    }
}
