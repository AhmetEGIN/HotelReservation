package com.egin.hotelreservation.city.controller;

import com.egin.hotelreservation.city.dto.mappers.CityMapper;
import com.egin.hotelreservation.city.dto.requests.CreateCityRequest;
import com.egin.hotelreservation.city.dto.requests.UpdateCityRequest;
import com.egin.hotelreservation.city.dto.responses.*;
import com.egin.hotelreservation.city.model.City;
import com.egin.hotelreservation.city.service.CitySaveService;
import com.egin.hotelreservation.city.service.CityService;
import com.egin.hotelreservation.common.model.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CityService cityService;
    private final CitySaveService citySaveService;

    public CityController(
            CityService cityService,
            CitySaveService citySaveService
    ) {
        this.cityService = cityService;
        this.citySaveService = citySaveService;
    }


    /**
     * Dışarıdan gönderilen bir Request ile {@link City} nesnesi ekleme işlemini yapmak
     * için kullanılan Controller katmanı metodudur.
     * @param request
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create')")
    public BaseResponse<CreateCityResponse> add(@RequestBody @Valid CreateCityRequest request) {
        final City city = this.citySaveService.add(request);
        final CreateCityResponse response = CityMapper.cityToCreateCityResponse(city);
        return BaseResponse.createdOf(response);
    }


    @GetMapping
    public BaseResponse<List<GetAllCitiesResponse>> getAllCities() {
        final List<City> cities = this.cityService.getAllCities();
        final List<GetAllCitiesResponse> response = cities.stream()
                .map(CityMapper::cityToGetAllCitiesResponse)
                .collect(Collectors.toList());
        return BaseResponse.successOf(response);
    }

    @GetMapping("/{id}")
    public BaseResponse<GetCityByIdResponse> getCityById(@PathVariable("id") String id) {
        final City city = this.cityService.getCityById(id);
        GetCityByIdResponse response = CityMapper.cityToGetCityByIdResponse(city);

        return BaseResponse.successOf(response);
    }

    @GetMapping("/country-code")
    public BaseResponse<List<GetAllCitiesByCountryCodeResponse>> getAllCitiesByCountryCode(@RequestParam String countryCode) {
        final List<City> cities = this.cityService.getAllCitiesByCountryCode(countryCode);
        final List<GetAllCitiesByCountryCodeResponse> response = cities.stream()
                .map(CityMapper::cityToGetAllCitiesByCountryCodeResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public BaseResponse<UpdateCityResponse> update(@PathVariable("id") String id, @RequestBody @Valid UpdateCityRequest request) {
        final City city = this.cityService.update(id, request);

        final UpdateCityResponse response = CityMapper.cityToUpdateCityResponse(city);
        return BaseResponse.successOf(response);
    }


}
