package com.egin.hotelreservation.city.dto.mappers;

import com.egin.hotelreservation.city.dto.requests.CreateCityRequest;
import com.egin.hotelreservation.city.dto.requests.UpdateCityRequest;
import com.egin.hotelreservation.city.dto.responses.*;
import com.egin.hotelreservation.city.model.City;
import org.springframework.stereotype.Component;

/**
 * City entity nesneleri ile ilgili Controller katmanında alınan veya verilen objelerin
 * map işlemini gerçekleştirecek olan sınıftır.
 */
@Component
public class CityMapper {

    /**
     * Controller'dan City Entity'si oluşturmak için gönderilen {@link CreateCityRequest} objesini
     * {@link City} objesine map'ler.
     * @param request
     * @return
     */
    public static City createCityRequestToCity(CreateCityRequest request) {

        return City.builder()
                .name(request.getName())
                .plateCode(request.getPlateCode())
                .countryCode(request.getCountryCode())
                .build();
    }

    /**
     * Controller katmanında bir {@link City} objesi oluşturulduğunda geri dönülecek olan
     * {@link CreateCityResponse} objesini oluşturur.
     * @param city
     * @return
     */
    public static CreateCityResponse cityToCreateCityResponse(City city) {

        return CreateCityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .plateCode(city.getPlateCode())
                .countryCode(city.getCountryCode())
                .build();
    }


    /**
     * Controller katmanında bir City nesnesi üzerinde Update işlemi yapıldığında
     * gelen City parametresini {@link UpdateCityResponse} nesnesine map'ler.
     * @param city
     * @return
     */
    public static UpdateCityResponse cityToUpdateCityResponse(City city) {

        return UpdateCityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .plateCode(city.getPlateCode())
                .countryCode(city.getCountryCode())
                .build();
    }


    /**
     * {@link City} entity nesneleri CountryCode parametresine göre filtrelendiğinde City nesnesini
     * Controller katmanında geri dönülecek olan {@link GetAllCitiesByCountryCodeResponse} nesnesine map'ler.
     * @param city
     * @return
     */
    public static GetAllCitiesByCountryCodeResponse cityToGetAllCitiesByCountryCodeResponse(City city) {

        return GetAllCitiesByCountryCodeResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .plateCode(city.getPlateCode())
                .countryCode(city.getCountryCode())
                .build();
    }


    /**
     * Controller katmanında tüm City nesneleri çağırıldığında {@link City} nesnesini
     * {@link GetAllCitiesResponse} nesnesine map'ler.
     * @param city
     * @return
     */
    public static GetAllCitiesResponse cityToGetAllCitiesResponse(City city) {

        return GetAllCitiesResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .plateCode(city.getPlateCode())
                .countryCode(city.getCountryCode())
                .build();
    }


    /**
     * Controller katmanında bir {@link City} nesnesi Id parametresine göre çağırıldığında
     * {@link City} nesnesini {@link GetCityByIdResponse} nesnesine map'ler.
     * @param city
     * @return
     */
    public static GetCityByIdResponse cityToGetCityByIdResponse(City city) {

        return GetCityByIdResponse.builder()
                .name(city.getName())
                .plateCode(city.getPlateCode())
                .countryCode(city.getCountryCode())
                .build();
    }

}
