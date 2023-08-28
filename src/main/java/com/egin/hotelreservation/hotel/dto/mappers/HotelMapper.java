package com.egin.hotelreservation.hotel.dto.mappers;

import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.city.model.City;
import com.egin.hotelreservation.hotel.dto.requests.hotelRequests.HotelRegisterRequest;
import com.egin.hotelreservation.hotel.dto.responses.hotelResponses.*;
import com.egin.hotelreservation.hotel.model.Hotel;
import org.springframework.stereotype.Component;

/**
 * {@link Hotel} entity nesneleri Controller katmanında dışa açıldığında request olarak alınması gereken
 * veya response olarak gönderilmesi gereken objelerin map işlemini gerçekleştiren class'dır.
 */
@Component
public class HotelMapper {


    /**
     * Bİr {@link Hotel} entity nesnesi Register işlemi yaparken alınan Request'i
     * Hotel nesnesine map'ler.
     * @param request
     * @param city
     * @return
     */
    public static Hotel hotelRegisterRequestToHotel(
            HotelRegisterRequest request,
            City city
    ) {

        return Hotel
                .builder()
                .email(request.getEmail())
                .name(request.getName())
                .star(request.getStar())
                .hotelType(request.getHotelType())
                .city(city)
                .build();

    }


    /**
     * Bir {@link Hotel} nesnesi Register işlemi yaptığında geri dönülecek olan Response DTO
     * nesnesini oluşturan mapper'dır.
     * @param hotel
     * @param city
     * @param tokenResponse
     * @return
     */
    public static HotelRegisterResponse toHotelRegisterResponse(
            Hotel hotel,
            City city,
            TokenResponse tokenResponse
    ) {

        return HotelRegisterResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .star(hotel.getStar())
                .hotelType(hotel.getHotelType())
                .hotelStatus(hotel.getHotelStatus())
                .cityName(city.getName())
                .countryCode(city.getCountryCode())
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }


    /**
     * Bir {@link Hotel}
     * @param hotel
     * @return
     */
    public static ChangeHotelStatusResponse hotelToChangeHotelStatusResponse(Hotel hotel) {

        return ChangeHotelStatusResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .star(hotel.getStar())
                .hotelStatus(hotel.getHotelStatus())
                .cityName(hotel.getCity().getName())
                .countryCode(hotel.getCity().getCountryCode())
                .build();
    }

    public static GetAllHotelsResponse hotelToGetAllHotelsResponse(Hotel hotel) {

        return GetAllHotelsResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .star(hotel.getStar())
                .hotelType(hotel.getHotelType())
                .hotelStatus(hotel.getHotelStatus())
                .cityName(hotel.getCity().getName())
                .countryCode(hotel.getCity().getCountryCode())
                .isActive(hotel.isActive())
                .isDeleted(hotel.isDeleted())
                .build();
    }


    public static GetAllPageableSortedFilteredByCityResponse hotelToGetAllPageableSortedFilteredByCityResponse(Hotel hotel) {


        return GetAllPageableSortedFilteredByCityResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .star(hotel.getStar())
                .hotelType(hotel.getHotelType())
                .hotelStatus(hotel.getHotelStatus())
                .cityName(hotel.getCity().getName())
                .countryCode(hotel.getCity().getCountryCode())
                .isActive(hotel.isActive())
                .isDeleted(hotel.isDeleted())
                .build();
    }

    public static GetHotelByEmailResponse hotelToGetHotelByEmailResponse(Hotel hotel){

        return GetHotelByEmailResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .star(hotel.getStar())
                .hotelType(hotel.getHotelType())
                .hotelStatus(hotel.getHotelStatus())
                .cityName(hotel.getCity().getName())
                .countryCode(hotel.getCity().getCountryCode())
                .isActive(hotel.isActive())
                .isDeleted(hotel.isDeleted())
                .build();
    }

    public static GetHotelByIdResponse hotelToGetHotelByIdResponse(Hotel hotel) {

        return GetHotelByIdResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .star(hotel.getStar())
                .hotelType(hotel.getHotelType())
                .hotelStatus(hotel.getHotelStatus())
                .cityName(hotel.getCity().getName())
                .countryCode(hotel.getCity().getCountryCode())
                .isActive(hotel.isActive())
                .isDeleted(hotel.isDeleted())
                .build();
    }


    public static UpdateHotelResponse hotelToUpdateHotelResponse(Hotel hotel) {

        return UpdateHotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .star(hotel.getStar())
                .hotelType(hotel.getHotelType())
                .hotelStatus(hotel.getHotelStatus())
                .cityName(hotel.getCity().getName())
                .countryCode(hotel.getCity().getCountryCode())
                .build();
    }

}
