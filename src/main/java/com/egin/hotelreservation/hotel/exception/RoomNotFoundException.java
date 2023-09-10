package com.egin.hotelreservation.hotel.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class RoomNotFoundException extends AppException {

    private static final String ROOM_NOT_FOUND = "Mit dem gegebenem Id wurde kein Room gefunden";

    public RoomNotFoundException() {
        super(ROOM_NOT_FOUND);
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
