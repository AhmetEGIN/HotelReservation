package com.egin.hotelreservation.hotel.dto.requests.hotelRequests;

import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.enums.HotelType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link Hotel} entity nesnesini oluşturmak için Controller katmanında istenecek olan
 * parametreleri içeren request DTO sınıfıdır.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelRegisterRequest {

    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @NotNull
    private String name;

    @Min(value = 1)
    @Max(value = 5)
    private int star;

    private HotelType hotelType;

    private String cityId;


}
