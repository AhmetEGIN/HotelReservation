package com.egin.hotelreservation.hotel.dto.responses.hotelResponses;

import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.enums.HotelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bir {@link Hotel} entity nesnesinin HotelStatus parametresi değiştirildiğinde
 * Controller katmanında geri dönülecek olan Response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeHotelStatusResponse {

    private String id;
    private String name;
    private int star;
    private HotelStatus hotelStatus;
    private String cityName;
    private String countryCode;

}
