package com.egin.hotelreservation.user.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class CustomerNotValidException extends AppException {

    private final static String CUSTOMER_NOT_VALID = "Ein Benutzer konnte mit den angegebenen Informationen nicht gefunden werden. " +
            "Wenn Sie noch kein Konto haben, registrieren Sie sich.";

    public CustomerNotValidException() {
        super(CUSTOMER_NOT_VALID);
    }

    public CustomerNotValidException(String message) {
        super(message);
    }
}
