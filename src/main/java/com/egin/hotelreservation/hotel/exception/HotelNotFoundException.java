package com.egin.hotelreservation.hotel.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class HotelNotFoundException extends AppException {

    private final static String HOTEL_NOT_FOUND = "Mit dem gegebenen Informationen wurde kein Hotel gefunden";

    public HotelNotFoundException() {
        super(HOTEL_NOT_FOUND);
    }

    public HotelNotFoundException(String message) {
        super(message);
    }
}
