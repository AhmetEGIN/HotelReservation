package com.egin.hotelreservation.roomBooking.exception;

import com.egin.hotelreservation.common.exception.AppException;

public class RoomIsAlreadyBookedException extends AppException {

    private static  final String ROOM_IS_ALREADY_BOOKED_IN_SELECTED_DATE_RANGE = "In ausgewählten Daten ist dieser Room schon vermietet";

    public RoomIsAlreadyBookedException() {
        super(ROOM_IS_ALREADY_BOOKED_IN_SELECTED_DATE_RANGE);
    }

    public RoomIsAlreadyBookedException(String message) {
        super(message);
    }
}
