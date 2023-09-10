package com.egin.hotelreservation.roomBooking.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class RoomBookingNotFoundException extends AppException {

    private static final String ROOM_BOOOKING_NOT_FOUND = "Mit dem gegebenem Information wurde kein Raumvermietung gefunden";

    public RoomBookingNotFoundException() {
        super(ROOM_BOOOKING_NOT_FOUND);
    }

    public RoomBookingNotFoundException(String message) {
        super(message);
    }
}
