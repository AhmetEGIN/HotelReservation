package com.egin.hotelreservation.hotel.service;


import com.egin.hotelreservation.hotel.dto.requests.hotelRequests.HotelRegisterRequest;
import com.egin.hotelreservation.hotel.dto.responses.hotelResponses.HotelRegisterResponse;

/**
 * Hotel entity nesnesi üzerinde Register işlemi yapacak olan HotelSaveService nesnesini
 * inject etmek için kullanılır.
 */
public interface HotelSaveService {

    HotelRegisterResponse add(HotelRegisterRequest request);

}
