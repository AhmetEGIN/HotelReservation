package com.egin.hotelreservation.city.service;


import com.egin.hotelreservation.city.dto.requests.CreateCityRequest;
import com.egin.hotelreservation.city.model.City;

public interface CitySaveService {

    City add(CreateCityRequest request);

}
