package com.egin.hotelreservation.user.exception;

import com.egin.hotelreservation.common.exception.AppException;
import com.egin.hotelreservation.user.model.Customer;

/**
 * Veri tabanında belirtilen parametrelere sahip bir {@link Customer} entity'si bulunamadığında
 * fırlatılacak olan Exception class'ıdır.
 */
public class CustomerNotFoundException extends AppException {

    private static final String DEFAULT_MESSAGE = "Kunde mit gegebenem Id kann nicht gefunden werden";

    public CustomerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
