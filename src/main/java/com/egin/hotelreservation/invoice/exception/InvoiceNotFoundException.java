package com.egin.hotelreservation.invoice.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class InvoiceNotFoundException extends AppException {

    private static final String INVOICE_NOT_FOUND = "Mit dem gegebenen Informationen wurde kein Rechnung gefunden";

    public InvoiceNotFoundException() {
        super(INVOICE_NOT_FOUND);
    }

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
