package com.egin.hotelreservation.security.exception;

import com.egin.hotelreservation.common.exception.AppException;
import com.egin.hotelreservation.security.constants.ExceptionMessages;

public class TokenNotFoundException extends AppException {


    public TokenNotFoundException() {
        super(ExceptionMessages.TOKEN_NOT_FOUND);
    }

    public TokenNotFoundException(String message) {
        super(message);
    }
}
