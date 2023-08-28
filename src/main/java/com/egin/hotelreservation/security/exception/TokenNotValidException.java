package com.egin.hotelreservation.security.exception;

import com.egin.hotelreservation.common.exception.AppException;
import com.egin.hotelreservation.security.constants.ExceptionMessages;

public class TokenNotValidException extends AppException {

    public TokenNotValidException() {
        super(ExceptionMessages.TOKEN_IS_NOT_VALID);
    }

    public TokenNotValidException(String message) {
        super(message);
    }
}
