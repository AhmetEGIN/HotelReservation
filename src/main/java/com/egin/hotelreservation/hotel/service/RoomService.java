package com.egin.hotelreservation.hotel.service;

import com.egin.hotelreservation.hotel.dto.requests.roomRequests.CreateRoomRequest;
import com.egin.hotelreservation.hotel.dto.requests.roomRequests.UpdateRoomRequest;
import com.egin.hotelreservation.hotel.dto.responses.roomResponses.CreateRoomResponse;
import com.egin.hotelreservation.hotel.model.Room;

import java.util.List;

public interface RoomService {

    CreateRoomResponse add(CreateRoomRequest request);
    List<Room> getAllRooms();
    Room update(String id, UpdateRoomRequest request);
    void delete(String id);
    void softDelete(String id, boolean isDeleted);
    Room getRoomById(String id);
    List<Room> getAllPageableSortedAndFiltered(int pageNo, int pageSize, String sortKey, String sortingDirection, String cityId, String hotelId);
    void changeActiveStatus(String id, boolean status);


}
