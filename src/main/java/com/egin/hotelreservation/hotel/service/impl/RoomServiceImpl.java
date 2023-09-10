package com.egin.hotelreservation.hotel.service.impl;

import com.egin.hotelreservation.common.exception.AppException;
import com.egin.hotelreservation.common.helper.identity.UserInContext;
import com.egin.hotelreservation.hotel.dto.mappers.RoomMapper;
import com.egin.hotelreservation.hotel.dto.requests.roomRequests.CreateRoomRequest;
import com.egin.hotelreservation.hotel.dto.requests.roomRequests.UpdateRoomRequest;
import com.egin.hotelreservation.hotel.dto.responses.roomResponses.CreateRoomResponse;
import com.egin.hotelreservation.hotel.exception.RoomNotFoundException;
import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.Room;
import com.egin.hotelreservation.hotel.repository.RoomRepository;
import com.egin.hotelreservation.hotel.service.HotelService;
import com.egin.hotelreservation.hotel.service.RoomService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;
    private final UserInContext userInContext;

    public RoomServiceImpl(
            RoomRepository roomRepository,
            HotelService hotelService,
            UserInContext userInContext) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
        this.userInContext = userInContext;
    }

    private Hotel getUser(){
        return this.hotelService.getHotelByEmail(this.userInContext.getUsername());
    }

    @Override
    public CreateRoomResponse add(CreateRoomRequest request) {

        Room room = RoomMapper.createRoomRequestToRoom(request, getUser());
        this.roomRepository.save(room);
        room.setActive(true);
        room.setDeleted(false);

        CreateRoomResponse response = RoomMapper.roomToCreateRoomResponse(room);
        return response;
    }

    @Override
    public List<Room> getAllRooms() {

        return this.roomRepository.findAll();
    }

    @Override
    public Room update(String id, UpdateRoomRequest request) {

        Room room = getRoomById(id);
        updateInfo(room, request);
        this.roomRepository.save(room);
        return room;
    }

    @Override
    public void delete(String id) {
        Room room = getRoomById(id);
        this.roomRepository.delete(room);

    }

    @Override
    public void softDelete(String id, boolean isDeleted) {

        Room room = getRoomById(id);
        room.setDeleted(isDeleted);
        this.roomRepository.save(room);
    }

    @Override
    public Room getRoomById(String id) {

        Room room = this.roomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);

        return room;
    }

    @Override
    public List<Room> getAllPageableSortedAndFiltered(int pageNo, int pageSize, String sortKey, String sortingDirection, String cityId, String hotelId) {

        List<Room> rooms = getAllPageable(pageNo, pageSize, sortKey, sortingDirection);

        Predicate<Room> cityCondition = cityId.isEmpty()
                ? (room -> true)
                : (room -> room.getHotel().getCity().getId().equals(cityId));

        Predicate<Room> hotelCondition = hotelId.isEmpty()
                ? (room -> true)
                : (room -> room.getHotel().getId().equals(hotelId));

        Stream<Room> roomStream = rooms.stream();
        List<Room> response = rooms.stream()
                .filter(cityCondition)
                .filter(hotelCondition)
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public void changeActiveStatus(String id, boolean status) {

        Room room = getRoomById(id);
        room.setActive(status);
        this.roomRepository.save(room);
    }


    private void updateInfo(Room room, UpdateRoomRequest request) {
        room.setDescription(request.getDescription());
        room.setPersonCapacity(request.getPersonCapacity());
        room.setDailyPrice(request.getDailyPrice());
        room.setHasTv(request.isHasTv());
        room.setHasMinibar(request.isHasMinibar());
        room.setHasKitchen(request.isHasKitchen());

    }

    private List<Room> getAllPageable(
            int pageNo, int pageSize, String sortKey, String sortingDirection
    ) {
        String sortKeyField;
        try {
            sortKeyField = Room.class.getDeclaredField(sortKey).getName();
        }catch (NoSuchFieldException e) {
            throw new AppException("Field not found");
        }

        Sort sort = Sort.by(Sort.Direction.valueOf(sortingDirection), sortKeyField);
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);

        List<Room> rooms = this.roomRepository.findAll(pageable).stream().collect(Collectors.toList());
        return rooms;

    }

}
