package com.egin.hotelreservation.city.dto.responses;

import com.egin.hotelreservation.city.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Id değeri verilen bir {@link City} nesnesi çağırıldığında Controller katmanında
 * geri dönülecek olan Response DTO class'ıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCityByIdResponse {


    private String name;
    private String plateCode;
    private String countryCode;

}
