package com.egin.hotelreservation.city.service.rules;

import com.egin.hotelreservation.city.exception.CityAlreadyExist;
import com.egin.hotelreservation.city.model.City;
import com.egin.hotelreservation.city.repository.CityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CityRules {

    private final CityRepository cityRepository;


    public CityRules(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public void isCityAlreadyExist(String name, String countryCode) throws CityAlreadyExist {

        List<City> cities = this.cityRepository.getCitiesByCountryCode(countryCode);
        Optional<City> city = cities.stream().filter(c -> c.getName() == name).findFirst();
        if (city.isPresent()) {
            throw new CityAlreadyExist();
        }
    }


}


