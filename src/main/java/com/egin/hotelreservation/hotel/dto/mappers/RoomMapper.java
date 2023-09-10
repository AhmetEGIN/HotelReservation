package com.egin.hotelreservation.hotel.dto.mappers;

import com.egin.hotelreservation.hotel.dto.requests.roomRequests.CreateRoomRequest;
import com.egin.hotelreservation.hotel.dto.responses.roomResponses.*;
import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {


    public static Room createRoomRequestToRoom(
            CreateRoomRequest request,
            Hotel hotel
    ) {

        return Room.builder()
                .description(request.getDescription())
                .floor(request.getFloor())
                .personCapacity(request.getPersonCapacity())
                .dailyPrice(request.getDailyPrice())
                .hasTv(request.isHasTv())
                .hasMinibar(request.isHasMinibar())
                .hasKitchen(request.isHasKitchen())
                .hotel(hotel)
                .build();
    }

    public static CreateRoomResponse roomToCreateRoomResponse(Room room) {

        return CreateRoomResponse.builder()
                .id(room.getId())
                .description(room.getDescription())
                .floor(room.getFloor())
                .personCapacity(room.getPersonCapacity())
                .dailyPrice(room.getDailyPrice())
                .hasTv(room.isHasTv())
                .hasMinibar(room.isHasMinibar())
                .hasKitchen(room.isHasKitchen())
                .hotelName(room.getHotel().getName())
                .build();
    }


    public static UpdateRoomResponse roomToUpdateRoomResponse(Room room) {

        return UpdateRoomResponse.builder()
                .id(room.getId())
                .description(room.getDescription())
                .floor(room.getFloor())
                .personCapacity(room.getPersonCapacity())
                .dailyPrice(room.getDailyPrice())
                .hasTv(room.isHasTv())
                .hasMinibar(room.isHasMinibar())
                .hasKitchen(room.isHasKitchen())
                .hotelName(room.getHotel().getName())
                .isDeleted(room.isDeleted())
                .isActive(room.isActive())
                .build();
    }

    public static GetAllPageableAndSortedFilteredByCityAndHotelRoomResponse roomToGetAllPageableAndSortedFilteredByCityAndHotelRoomResponse(Room room) {

        return GetAllPageableAndSortedFilteredByCityAndHotelRoomResponse.builder()
                .id(room.getId())
                .description(room.getDescription())
                .floor(room.getFloor())
                .personCapacity(room.getPersonCapacity())
                .dailyPrice(room.getDailyPrice())
                .hasTv(room.isHasTv())
                .hasMinibar(room.isHasMinibar())
                .hasKitchen(room.isHasKitchen())
                .hotelName(room.getHotel().getName())
                .isDeleted(room.isDeleted())
                .isActive(room.isActive())
                .build();
    }

    public static GetAllRoomsResponse roomToGetAllRoomsResponse(Room room) {

        return GetAllRoomsResponse.builder()
                .id(room.getId())
                .description(room.getDescription())
                .floor(room.getFloor())
                .personCapacity(room.getPersonCapacity())
                .dailyPrice(room.getDailyPrice())
                .hasTv(room.isHasTv())
                .hasMinibar(room.isHasMinibar())
                .hasKitchen(room.isHasKitchen())
                .hotelName(room.getHotel().getName())
                .isDeleted(room.isDeleted())
                .isActive(room.isActive())
                .build();
    }

    public static GetRoomByIdResponse roomToGetRoomByIdResponse(Room room) {

        return GetRoomByIdResponse.builder()
                .id(room.getId())
                .description(room.getDescription())
                .floor(room.getFloor())
                .personCapacity(room.getPersonCapacity())
                .dailyPrice(room.getDailyPrice())
                .hasTv(room.isHasTv())
                .hasMinibar(room.isHasMinibar())
                .hasKitchen(room.isHasKitchen())
                .hotelName(room.getHotel().getName())
                .isDeleted(room.isDeleted())
                .isActive(room.isActive())
                .build();
    }

}
