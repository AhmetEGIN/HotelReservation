package com.egin.hotelreservation.hotel.service;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.hotel.dto.requests.hotelRequests.UpdateHotelRequest;
import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.enums.HotelStatus;

import java.util.List;

/**
 * HotelService nesnesini inject etmek için kullanılacak interface'dir.
 */
public interface HotelService {

    TokenResponse loginHotel(LoginRequest loginRequest);
    Hotel getHotelById(String id);
    Hotel update(UpdateHotelRequest request);
    List<Hotel> getAllHotels();
    Hotel getHotelByEmail(String email);
    void delete(String id);
    void softDelete(boolean isDeleted);
    Hotel changeHotelStatus(String id, HotelStatus hotelStatus);
    List<Hotel> getAllPageableAndSortedAndFilteredByCity(int pageNo, int pageSize, String sortKey, String sortingDirection, String cityId);



}
