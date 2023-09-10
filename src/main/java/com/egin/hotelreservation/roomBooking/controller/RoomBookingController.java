package com.egin.hotelreservation.roomBooking.controller;

import com.egin.hotelreservation.common.model.response.BaseResponse;
import com.egin.hotelreservation.roomBooking.dto.mappers.RoomBookingMapper;
import com.egin.hotelreservation.roomBooking.dto.requests.roomBookingRequests.CreateRoomBookingRequest;
import com.egin.hotelreservation.roomBooking.dto.responses.roomBookingResponses.*;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import com.egin.hotelreservation.roomBooking.service.RoomBookingService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/room-bookings")
public class RoomBookingController {

    private final RoomBookingService roomBookingService;

    public RoomBookingController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('customer:create', 'admin:create', 'hotel:create')")
    public BaseResponse<CreateRoomBookingResponse> add(@RequestBody @Valid CreateRoomBookingRequest request) {
        final RoomBooking booking = this.roomBookingService.add(request);
        CreateRoomBookingResponse response = RoomBookingMapper.roomBookingToCreateRoomBookingResponse(booking);
        return BaseResponse.createdOf(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('customer:read', 'admin:read', 'hotel:read')")
    public BaseResponse<GetRoomBookingByIdResponse> getRoomBookingById(@PathVariable("id") String id){

        final RoomBooking booking = this.roomBookingService.getRoomBookingById(id);

        return BaseResponse.successOf(RoomBookingMapper.roomBookingToGetRoomBookingByIdResponse(booking));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('hotel:read', 'admin:read')")
    public BaseResponse<List<GetAllRoomBookingsResponse>> getAllRoomBookings() {

        List<GetAllRoomBookingsResponse> response = this.roomBookingService.getAllRoomBookings()
                .stream()
                .map(RoomBookingMapper::roomBookingToGetAllRoomBookingsResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('customer:read', 'hotel:read', 'admin:read')")
    public BaseResponse<List<GetAllRoomBookingsByCustomerResponse>> getAllRoomBookingsByCustomer(){

        List<GetAllRoomBookingsByCustomerResponse> response = this.roomBookingService.getAllRoomBookingsByCustomer()
                .stream()
                .map(RoomBookingMapper::roomBookingToGetAllRoomBookingsByCustomerResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'customer:read', 'hotel:read')")
    public BaseResponse<List<GetAllRoomBookingsByCustomerIdResponse>> getAllRoomBookingsByCustomerId(@PathVariable("id") String id){

        List<GetAllRoomBookingsByCustomerIdResponse> response = this.roomBookingService.getAllRoomBookingsByCustomerId(id)
                .stream()
                .map(RoomBookingMapper::roomBookingToGetAllRoomBookingsByCustomerIdResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('customer:delete', 'admin:delete', 'hotel:delete')")
    public BaseResponse deleteRoomBooking(@PathVariable("id") String id){

        this.roomBookingService.deleteRoomBooking(id);

        return BaseResponse.NO_CONTENT;
    }

    @GetMapping("/price/{roomId}")
    public BaseResponse<Double> getTotalPrice(
            @PathVariable("roomId") String roomId,
            @RequestParam(name = "startDate") LocalDateTime startDate,
            @RequestParam(name = "endDate") LocalDateTime endDate){

        Double response = this.roomBookingService.getTotalPrice(roomId, startDate, endDate);
//TODO: Fiyat bilgisi görmek için sisteme login olmak gerekmemeli.
        return BaseResponse.successOf(response);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('customer:update', 'admin:update', 'hotel:update')")
    public BaseResponse checkOut(@PathVariable("id") String roomBookingId){

        this.roomBookingService.checkOut(roomBookingId);

        return BaseResponse.SUCCESS;
    }

}
