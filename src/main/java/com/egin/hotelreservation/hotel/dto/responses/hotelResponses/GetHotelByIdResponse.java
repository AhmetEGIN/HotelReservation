package com.egin.hotelreservation.hotel.dto.responses.hotelResponses;

import com.egin.hotelreservation.hotel.model.enums.HotelStatus;
import com.egin.hotelreservation.hotel.model.enums.HotelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hotel entity nesnesi Id değerine göre çağırıldığında Controller katmanında geri
 * dönülecek olan Response DTO class'ıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetHotelByIdResponse {

    private String id;
    private String name;
    private int star;
    private HotelType hotelType;
    private HotelStatus hotelStatus;
    private boolean isActive;
    private boolean isDeleted;
    private String cityName;
    private String countryCode;

}
