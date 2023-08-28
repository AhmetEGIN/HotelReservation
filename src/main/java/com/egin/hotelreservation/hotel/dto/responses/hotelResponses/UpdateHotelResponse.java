package com.egin.hotelreservation.hotel.dto.responses.hotelResponses;

import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.enums.HotelStatus;
import com.egin.hotelreservation.hotel.model.enums.HotelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controller katmanında bir {@link Hotel} entity nesnesi üzerinde Update işlemi yapıldığı
 * zaman geri dönülecek olan Response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateHotelResponse {

    private String id;
    private String name;
    private int star;
    private HotelStatus hotelStatus;
    private HotelType hotelType;
    private String cityName;
    private String countryCode;


}
