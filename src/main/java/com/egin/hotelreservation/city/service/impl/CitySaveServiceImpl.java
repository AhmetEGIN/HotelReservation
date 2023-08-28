package com.egin.hotelreservation.city.service.impl;

import com.egin.hotelreservation.city.dto.mappers.CityMapper;
import com.egin.hotelreservation.city.dto.requests.CreateCityRequest;
import com.egin.hotelreservation.city.model.City;
import com.egin.hotelreservation.city.repository.CityRepository;
import com.egin.hotelreservation.city.service.CitySaveService;
import com.egin.hotelreservation.city.service.rules.CityRules;
import org.springframework.stereotype.Service;

/**
 * City entity nesnesini ekleme işlemi yapmak için kullanılan Service katmanı class'ıdır.
 */
@Service
public class CitySaveServiceImpl implements CitySaveService {

    private final CityRepository cityRepository;
    private final CityRules cityRules;

    public CitySaveServiceImpl(
            CityRepository cityRepository,
            CityRules cityRules
    ) {
        this.cityRepository = cityRepository;
        this.cityRules = cityRules;
    }


    /**
     * {@link City} nesnesini eklemek için kullanılan Service katmanı metodudur.
     * @param request
     * @return
     */
    @Override
    public City add(CreateCityRequest request) {

        this.cityRules.isCityAlreadyExist(request.getName(), request.getCountryCode());

        final City city = CityMapper.createCityRequestToCity(request);

        return this.cityRepository.save(city);
    }
}
