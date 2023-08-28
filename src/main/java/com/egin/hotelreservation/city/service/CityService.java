package com.egin.hotelreservation.city.service;


import com.egin.hotelreservation.city.dto.requests.UpdateCityRequest;
import com.egin.hotelreservation.city.model.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();
    City getCityById(String id);
    List<City> getAllCitiesByCountryCode(String countryCode);
    City update(String id, UpdateCityRequest request);

}
