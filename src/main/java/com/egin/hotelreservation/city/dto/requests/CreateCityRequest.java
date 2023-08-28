package com.egin.hotelreservation.city.dto.requests;

import com.egin.hotelreservation.city.model.City;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bir {@link City} nesnesi oluşturmak için gerekli olan parametreleri içeren
 * Request DTO class'ıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCityRequest {

    @NotNull
    private String name;

    private String plateCode;

    private String countryCode;

}
