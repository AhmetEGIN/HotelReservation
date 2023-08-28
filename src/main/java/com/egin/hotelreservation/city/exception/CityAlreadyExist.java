package com.egin.hotelreservation.city.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class CityAlreadyExist extends AppException {

    private static final String CITY_ALREADY_EXIST = "Diese Stadt wurde schon hinzugefügt";

    public CityAlreadyExist() {
        super(CITY_ALREADY_EXIST);
    }

    public CityAlreadyExist(String message) {
        super(message);
    }
}
