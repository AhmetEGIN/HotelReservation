package com.egin.hotelreservation.roomBooking.dto.mappers;

import com.egin.hotelreservation.hotel.model.Room;
import com.egin.hotelreservation.roomBooking.dto.requests.roomBookingRequests.CreateRoomBookingRequest;
import com.egin.hotelreservation.roomBooking.dto.responses.roomBookingResponses.*;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import com.egin.hotelreservation.user.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class RoomBookingMapper {


    public static RoomBooking createRoomBookingRequestToRoomBooking(
            CreateRoomBookingRequest request,
            Double price,
            Room room,
            Customer customer
    ) {

        return RoomBooking.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .price(price)
                .customer(customer)
                .room(room)
                .build();

    }

    public static CreateRoomBookingResponse roomBookingToCreateRoomBookingResponse(RoomBooking roomBooking) {

        return CreateRoomBookingResponse.builder()
                .id(roomBooking.getId())
                .startDate(roomBooking.getStartDate())
                .endDate(roomBooking.getEndDate())
                .price(roomBooking.getPrice())
                .hotelName(roomBooking.getRoom().getHotel().getName())
                .roomId(roomBooking.getRoom().getId())
                .customerFirstName(roomBooking.getCustomer().getFirstName())
                .customerLastName(roomBooking.getCustomer().getLastName())
                .build();
    }

    public static GetRoomBookingByIdResponse roomBookingToGetRoomBookingByIdResponse(RoomBooking roomBooking) {

        return GetRoomBookingByIdResponse.builder()
                .id(roomBooking.getId())
                .startDate(roomBooking.getStartDate())
                .endDate(roomBooking.getEndDate())
                .price(roomBooking.getPrice())
                .hotelName(roomBooking.getRoom().getHotel().getName())
                .roomId(roomBooking.getRoom().getId())
                .customerFirstName(roomBooking.getCustomer().getFirstName())
                .customerLastName(roomBooking.getCustomer().getLastName())
                .isActive(roomBooking.isActive())
                .isDeleted(roomBooking.isDeleted())
                .build();
    }


    public static UpdateRoomBookingResponse roomBookingToUpdateRoomBookingResponse(RoomBooking roomBooking) {

        return UpdateRoomBookingResponse.builder()
                .id(roomBooking.getId())
                .startDate(roomBooking.getStartDate())
                .endDate(roomBooking.getEndDate())
                .hotelName(roomBooking.getRoom().getHotel().getName())
                .price(roomBooking.getPrice())
                .roomId(roomBooking.getRoom().getId())
                .customerFirstName(roomBooking.getCustomer().getFirstName())
                .customerLastName(roomBooking.getCustomer().getLastName())
                .isActive(roomBooking.isActive())
                .isDeleted(roomBooking.isDeleted())
                .build();
    }


    public static GetAllRoomBookingsResponse roomBookingToGetAllRoomBookingsResponse(RoomBooking roomBooking) {

        return GetAllRoomBookingsResponse.builder()
                .id(roomBooking.getId())
                .startDate(roomBooking.getStartDate())
                .endDate(roomBooking.getEndDate())
                .hotelName(roomBooking.getRoom().getHotel().getName())
                .price(roomBooking.getPrice())
                .roomId(roomBooking.getRoom().getId())
                .customerFirstName(roomBooking.getCustomer().getFirstName())
                .customerLastName(roomBooking.getCustomer().getLastName())
                .isActive(roomBooking.isActive())
                .isDeleted(roomBooking.isDeleted())
                .build();
    }

    public static GetAllRoomBookingsByCustomerIdResponse roomBookingToGetAllRoomBookingsByCustomerIdResponse(RoomBooking roomBooking) {

        return GetAllRoomBookingsByCustomerIdResponse.builder()
                .id(roomBooking.getId())
                .startDate(roomBooking.getStartDate())
                .endDate(roomBooking.getEndDate())
                .hotelName(roomBooking.getRoom().getHotel().getName())
                .price(roomBooking.getPrice())
                .roomId(roomBooking.getRoom().getId())
                .customerFirstName(roomBooking.getCustomer().getFirstName())
                .customerLastName(roomBooking.getCustomer().getLastName())
                .isActive(roomBooking.isActive())
                .isDeleted(roomBooking.isDeleted())
                .build();
    }

    public static GetAllRoomBookingsByCustomerResponse roomBookingToGetAllRoomBookingsByCustomerResponse(RoomBooking roomBooking) {

        return GetAllRoomBookingsByCustomerResponse.builder()
                .id(roomBooking.getId())
                .startDate(roomBooking.getStartDate())
                .endDate(roomBooking.getEndDate())
                .hotelName(roomBooking.getRoom().getHotel().getName())
                .price(roomBooking.getPrice())
                .roomId(roomBooking.getRoom().getId())
                .customerFirstName(roomBooking.getCustomer().getFirstName())
                .customerLastName(roomBooking.getCustomer().getLastName())
                .isActive(roomBooking.isActive())
                .isDeleted(roomBooking.isDeleted())
                .build();
    }

}
