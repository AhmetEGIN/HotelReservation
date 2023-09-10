package com.egin.hotelreservation.payment.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class PaymentNotFoundException extends AppException {

    private static final String PAYMENT_NOT_FOUND = "Mit dem gegebenem Informationen wurde kein Payment gefunden";

    public PaymentNotFoundException() {
        super(PAYMENT_NOT_FOUND);
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
