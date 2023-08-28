package com.egin.hotelreservation.user.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class AdminNotFoundException extends AppException {

    private static final String ADMIN_NOT_FOUND = "Admin nicht gefunden";

    public AdminNotFoundException() {
        super(ADMIN_NOT_FOUND);
    }

    public AdminNotFoundException(String message) {
        super(message);
    }
}
