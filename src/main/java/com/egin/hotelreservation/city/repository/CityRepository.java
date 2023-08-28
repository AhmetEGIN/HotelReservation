package com.egin.hotelreservation.city.repository;

import com.egin.hotelreservation.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * City entity nesnesi üzerinde veri tabanı işlemleri yapmamızı sağlayan
 * Jpa Repository interface'idir.
 */
@Repository
public interface CityRepository extends JpaRepository<City, String> {


    List<City> getCitiesByCountryCode(String countryCode);

}
