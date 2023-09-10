package com.egin.hotelreservation.hotel.controller;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.common.model.response.BaseResponse;
import com.egin.hotelreservation.hotel.dto.mappers.HotelMapper;
import com.egin.hotelreservation.hotel.dto.requests.hotelRequests.HotelRegisterRequest;
import com.egin.hotelreservation.hotel.dto.requests.hotelRequests.UpdateHotelRequest;
import com.egin.hotelreservation.hotel.dto.responses.hotelResponses.*;
import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.enums.HotelStatus;
import com.egin.hotelreservation.hotel.service.HotelSaveService;
import com.egin.hotelreservation.hotel.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Hotel entity nesnelerini dışarıdan ulaşıma açtığımız Controller katmanı class'ıdır.
 */
@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private final HotelSaveService hotelSaveService;
    private final HotelService hotelService;

    public HotelController(
            HotelSaveService hotelSaveService,
            HotelService hotelService
    ) {
        this.hotelSaveService = hotelSaveService;
        this.hotelService = hotelService;
    }


    @PostMapping
    public BaseResponse<HotelRegisterResponse> add(@RequestBody @Valid HotelRegisterRequest request) {
        final HotelRegisterResponse response = this.hotelSaveService.add(request);
        return BaseResponse.createdOf(response);
    }


    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse response = this.hotelService.loginHotel(loginRequest);
        return BaseResponse.successOf(response);
    }


    @GetMapping("/{id}")
    public BaseResponse<GetHotelByIdResponse> getHotelById(@PathVariable("id") String id) {
        final Hotel hotel = this.hotelService.getHotelById(id);
        final GetHotelByIdResponse response = HotelMapper.hotelToGetHotelByIdResponse(hotel);

        return BaseResponse.successOf(response);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('hotel:update', 'admin:update')")
    public BaseResponse<UpdateHotelResponse> update(@RequestBody @Valid UpdateHotelRequest request) {
        final Hotel hotel = this.hotelService.update(request);
        final UpdateHotelResponse response = HotelMapper.hotelToUpdateHotelResponse(hotel);

        return BaseResponse.successOf(response);
    }

    @GetMapping("/all")
    public BaseResponse<List<GetAllHotelsResponse>> getAllHotels() {
        final List<Hotel> hotels = this.hotelService.getAllHotels();
        final List<GetAllHotelsResponse> response = hotels.stream()
                .map(HotelMapper::hotelToGetAllHotelsResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);
    }

    @GetMapping("/email")
    public BaseResponse<GetHotelByEmailResponse> getHotelByEmail(@RequestParam String email) {
        final Hotel hotel = this.hotelService.getHotelByEmail(email);
        final GetHotelByEmailResponse response = HotelMapper.hotelToGetHotelByEmailResponse(hotel);

        return BaseResponse.successOf(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:delete', 'hotel:delete')")
    public BaseResponse delete(@PathVariable("id") String id) {
        this.hotelService.delete(id);

        return BaseResponse.NO_CONTENT;
    }

    @DeleteMapping("/soft")
    @PreAuthorize("hasAnyAuthority('admin:delete', 'hotel:delete')")
    public BaseResponse softDelete(@RequestParam boolean isDeleted) {
        this.hotelService.softDelete(isDeleted);

        return BaseResponse.NO_CONTENT;
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:update', 'hotel:update')")
    public BaseResponse<ChangeHotelStatusResponse> changeHotelStatus(
            @PathVariable("id") String id,
            @RequestParam HotelStatus hotelStatus) {
        final Hotel hotel = this.hotelService.changeHotelStatus(id, hotelStatus);
        final ChangeHotelStatusResponse response = HotelMapper.hotelToChangeHotelStatusResponse(hotel);

        return BaseResponse.successOf(response);
    }

    @GetMapping
    public BaseResponse<List<GetAllPageableSortedFilteredByCityResponse>> getAllPageableAndSortedAndFilteredByCity(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortKey", required = false, defaultValue = "star") String sortKey,
            @RequestParam(value = "sortingDirection", required = false, defaultValue = "ASC") String sortingDirection,
            @RequestParam(value = "cityId", required = false, defaultValue = "") String cityId
    ) {
        final List<Hotel> hotels = this.hotelService.getAllPageableAndSortedAndFilteredByCity(
                pageNo, pageSize, sortKey, sortingDirection, cityId
        );

        final List<GetAllPageableSortedFilteredByCityResponse> response = hotels.stream()

                .map(HotelMapper::hotelToGetAllPageableSortedFilteredByCityResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);
    }



}
