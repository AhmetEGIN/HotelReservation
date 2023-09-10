package com.egin.hotelreservation.roomBooking.service;

import com.egin.hotelreservation.roomBooking.dto.requests.roomBookingRequests.CreateRoomBookingRequest;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomBookingService {

    RoomBooking add(CreateRoomBookingRequest request);

    RoomBooking getRoomBookingById(String id);
    List<RoomBooking> getAllRoomBookings();
    List<RoomBooking> getAllRoomBookingsByCustomer();
    List<RoomBooking> getAllRoomBookingsByCustomerId(String id);
    void deleteRoomBooking(String id);
    Double getTotalPrice(String roomId, LocalDateTime startDate, LocalDateTime endDate);
    void checkOut(String roomBookingId);

}
