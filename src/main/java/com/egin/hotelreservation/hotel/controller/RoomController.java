package com.egin.hotelreservation.hotel.controller;

import com.egin.hotelreservation.common.model.response.BaseResponse;
import com.egin.hotelreservation.hotel.dto.mappers.RoomMapper;
import com.egin.hotelreservation.hotel.dto.requests.roomRequests.CreateRoomRequest;
import com.egin.hotelreservation.hotel.dto.requests.roomRequests.UpdateRoomRequest;
import com.egin.hotelreservation.hotel.dto.responses.roomResponses.*;
import com.egin.hotelreservation.hotel.model.Room;
import com.egin.hotelreservation.hotel.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('hotel:create', 'admin:create')")
    public BaseResponse<CreateRoomResponse> add(@RequestBody @Valid CreateRoomRequest request){

        return BaseResponse.createdOf(this.roomService.add(request));
    }

    @GetMapping("/all")
    public BaseResponse<List<GetAllRoomsResponse>> getAllRooms() {

        List<Room> rooms = this.roomService.getAllRooms();
        List<GetAllRoomsResponse> response = rooms.stream()
                .map(RoomMapper::roomToGetAllRoomsResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('hotel:update', 'admin:update')")
    public BaseResponse<UpdateRoomResponse> update(@PathVariable("id") String id, @RequestBody @Valid UpdateRoomRequest request) {

        Room room = this.roomService.update(id, request);
        UpdateRoomResponse response = RoomMapper.roomToUpdateRoomResponse(room);

        return BaseResponse.successOf(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('hotel:delete', 'admin:delete')")
    public BaseResponse delete(@PathVariable("id") String id) {

        this.roomService.delete(id);
        return BaseResponse.NO_CONTENT;
    }

    @DeleteMapping("/soft/{id}")
    public BaseResponse softDelete(@PathVariable("id") String id, @RequestParam boolean isDeleted) {
        this.roomService.softDelete(id, isDeleted);

        return BaseResponse.NO_CONTENT;
    }

    @GetMapping("/{id}")
    public BaseResponse<GetRoomByIdResponse> getRoomById(@PathVariable("id") String id) {
        Room room = this.roomService.getRoomById(id);
        GetRoomByIdResponse response = RoomMapper.roomToGetRoomByIdResponse(room);

        return BaseResponse.successOf(response);
    }

    @GetMapping
    public BaseResponse<List<GetAllPageableAndSortedFilteredByCityAndHotelRoomResponse>> getAllPageableSortedAndFiltered(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortKey", required = false, defaultValue = "description") String sortKey,
            @RequestParam(value = "sortingDirection", required = false, defaultValue = "ASC") String sortingDirection,
            @RequestParam(value = "cityId", required = false, defaultValue = "") String cityId,
            @RequestParam(value = "hotelId", required = false, defaultValue = "") String hotelId
    ) {

        List<Room> rooms = this.roomService.getAllPageableSortedAndFiltered(pageNo, pageSize, sortKey, sortingDirection, cityId, hotelId);

        List<GetAllPageableAndSortedFilteredByCityAndHotelRoomResponse> response = rooms.stream()
                .map(RoomMapper::roomToGetAllPageableAndSortedFilteredByCityAndHotelRoomResponse)
                .collect(Collectors.toList());
        return BaseResponse.successOf(response);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('hotel:update', 'admin:update')")
    public BaseResponse changeActiveStatus(@PathVariable("id") String id, @RequestParam boolean status) {

        this.roomService.changeActiveStatus(id, status);

        return BaseResponse.SUCCESS;
    }


}
