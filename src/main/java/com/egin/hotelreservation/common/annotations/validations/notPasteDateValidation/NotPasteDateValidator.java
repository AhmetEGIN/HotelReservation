package com.egin.hotelreservation.common.annotations.validations.notPasteDateValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class NotPasteDateValidator implements ConstraintValidator<NotPasteDate, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        return !localDateTime.isBefore(LocalDateTime.now());
    }
}
