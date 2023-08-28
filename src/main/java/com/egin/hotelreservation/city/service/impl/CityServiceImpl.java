package com.egin.hotelreservation.city.service.impl;

import com.egin.hotelreservation.city.dto.mappers.CityMapper;
import com.egin.hotelreservation.city.dto.requests.UpdateCityRequest;
import com.egin.hotelreservation.city.exception.CityNotFoundException;
import com.egin.hotelreservation.city.model.City;
import com.egin.hotelreservation.city.repository.CityRepository;
import com.egin.hotelreservation.city.service.CityService;
import com.egin.hotelreservation.city.service.rules.CityRules;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * City Entity nesneleri üzerinde işlem yapmayı sağlayan Service katmanı class'ıdır.
 */
@Service
public class CityServiceImpl implements CityService {


    private final CityRepository cityRepository;
    private final CityRules cityRules;



    public CityServiceImpl(
            CityRepository cityRepository,
            CityRules cityRules
    ) {
        this.cityRepository = cityRepository;
        this.cityRules = cityRules;
    }

    /**
     * Sistemde kayıtlı olan tüm City entity nesnelerini getimek için kullanılan
     * Service katmanı metodudur.
     * @return
     */
    @Override
    public List<City> getAllCities() {

        return this.cityRepository.findAll();
    }

    /**
     * Id değeri verilen {@link City} nesnesini getirmek için kullanılan
     * Service katmanı metodudur.
     * @param id
     * @return
     */
    @Override
    public City getCityById(String id) {

        return this.cityRepository.findById(id)
                .orElseThrow(CityNotFoundException::new);
    }

    /**
     * {@link City} nesnelerini CountryCode parametresine göre filtrelemek
     * için kullanılan Service katmanı metodudur.
     * @param countryCode
     * @return
     */
    @Override
    public List<City> getAllCitiesByCountryCode(String countryCode) {

        return this.cityRepository.getCitiesByCountryCode(countryCode);
    }


    /**
     * Kayıtlı bir City nesnesinin bilgilerini güncellemek için kullanılan
     * Service katmanı metodudur.
     * @param id
     * @param request
     * @return
     */
    @Override
    public City update(String id, UpdateCityRequest request) {

        final City city = this.getCityById(id);
        updateCity(city, request);
        this.cityRepository.save(city);

        return city;
    }


    /**
     * Verilen {@link City} ve {@link UpdateCityRequest} objeleri üzerinde birleştirme
     * işlemini yapan yardımcı metottur.
     * @param city
     * @param request
     */
    private void updateCity(City city, UpdateCityRequest request) {
    // TODO: Update City çalışmıyor

        city.setName(request.getName());
        city.setCountryCode(request.getCountryCode());
        city.setPlateCode(request.getPlateCode());
    }
}
