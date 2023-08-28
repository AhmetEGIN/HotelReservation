package com.egin.hotelreservation.auth.exceptions;

public class PasswordNotValidException extends RuntimeException {

    private static final String PASSWORD_NOT_VALID = "Password ungültig";

    public PasswordNotValidException() {
        super(PASSWORD_NOT_VALID);
    }

    public PasswordNotValidException(String message) {
        super(message);
    }
}
