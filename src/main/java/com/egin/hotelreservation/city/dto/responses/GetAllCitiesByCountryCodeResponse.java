package com.egin.hotelreservation.city.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * City nesneleri CountryCode parametresine göre filtrelendiğinde Controller katmanında
 * geri dönülecek olan Response DTO class'ıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllCitiesByCountryCodeResponse {

    private String id;
    private String name;
    private String plateCode;
    private String countryCode;

}
