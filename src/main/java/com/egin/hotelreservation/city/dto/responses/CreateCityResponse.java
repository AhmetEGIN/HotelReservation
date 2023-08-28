package com.egin.hotelreservation.city.dto.responses;

import com.egin.hotelreservation.city.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bir {@link City} nesnesi oluşturulduğunda Controller katmanında geri dönülecek
 * olan Response DTO class'ıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCityResponse {

    private String id;
    private String name;
    private String plateCode;
    private String countryCode;


}
