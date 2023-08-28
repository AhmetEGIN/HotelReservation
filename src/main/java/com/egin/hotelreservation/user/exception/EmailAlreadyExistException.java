package com.egin.hotelreservation.user.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class EmailAlreadyExistException extends AppException {

    private static final String DEFAULT_MESSAGE = "Diese Email Adresse wird bereits verwendet";

    public EmailAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
