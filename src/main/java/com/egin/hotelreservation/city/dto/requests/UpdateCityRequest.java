package com.egin.hotelreservation.city.dto.requests;

import com.egin.hotelreservation.city.model.City;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bir {@link City} nesnesinde güncellenmeye izin verilen parametreleri içeren
 * Request DTO class'ıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCityRequest {

    @NotNull
    private String name;

    private String plateCode;

    private String countryCode;

}
