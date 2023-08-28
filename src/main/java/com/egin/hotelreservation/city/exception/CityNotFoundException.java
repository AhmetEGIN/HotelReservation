package com.egin.hotelreservation.city.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class CityNotFoundException extends AppException {

    private static final String CITY_NOT_FOUND = "Mit gegebenen Informationen wurde keine Stadt gefunden";

    public CityNotFoundException() {
        super(CITY_NOT_FOUND);
    }

    public CityNotFoundException(String message) {
        super(message);
    }
}
