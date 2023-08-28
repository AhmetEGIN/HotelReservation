package com.egin.hotelreservation.hotel.dto.requests.hotelRequests;

import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.enums.HotelType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kayıtlı bir {@link Hotel} nesnesinin bilgilerini güncellemek için Controller katmanında
 * istenecek parametreleri içeren Request DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateHotelRequest {

    @NotNull
    private String name;

    @Min(value = 1)
    @Max(value = 5)
    private int star;

    private HotelType hotelType;

}
