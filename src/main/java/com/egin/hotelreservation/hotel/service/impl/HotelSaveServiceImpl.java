package com.egin.hotelreservation.hotel.service.impl;

import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.auth.service.AuthService;
import com.egin.hotelreservation.city.model.City;
import com.egin.hotelreservation.city.service.CityService;
import com.egin.hotelreservation.hotel.dto.mappers.HotelMapper;
import com.egin.hotelreservation.hotel.dto.requests.hotelRequests.HotelRegisterRequest;
import com.egin.hotelreservation.hotel.dto.responses.hotelResponses.HotelRegisterResponse;
import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.repository.HotelRepository;
import com.egin.hotelreservation.hotel.service.HotelSaveService;
import com.egin.hotelreservation.hotel.service.rules.HotelBusinessRules;
import com.egin.hotelreservation.security.model.user.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * Bir {@link Hotel} entity nesnesi ile Register işlemi yapmak için kullanılan Service katmanı class'ıdır.
 */
@Repository
public class HotelSaveServiceImpl implements HotelSaveService {

    private final HotelRepository hotelRepository;
    private final HotelBusinessRules hotelBusinessRules;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final CityService cityService;

    public HotelSaveServiceImpl(
            HotelRepository hotelRepository,
            HotelBusinessRules hotelBusinessRules,
            PasswordEncoder passwordEncoder,
            AuthService authService,
            CityService cityService
    ) {
        this.hotelRepository = hotelRepository;
        this.hotelBusinessRules = hotelBusinessRules;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.cityService = cityService;
    }

    /**
     * {@link Hotel} nesnesinin Register işlemlerini yapmak için kullanılan Service katmanı metodudur.
     * @param request
     * @return HotelRegisterResponse
     */
    @Override
    public HotelRegisterResponse add(HotelRegisterRequest request) {

        this.hotelBusinessRules.isEmailExist(request.getEmail());
        System.out.println(request.getEmail());
        final City city = this.cityService.getCityById(request.getCityId());
        System.out.println(city.getName());
        final Hotel hotel = HotelMapper.hotelRegisterRequestToHotel(request, city);
        System.out.println("********");
        System.out.println(hotel.getName());
        hotel.setPassword(passwordEncoder.encode(request.getPassword()));
        hotel.setRole(Role.HOTEL);
        hotel.setActive(true);
        hotel.setDeleted(false);

        final TokenResponse tokenResponse = this.authService.generateToken(hotel);
        System.out.println(tokenResponse.getAccessToken());
        final HotelRegisterResponse response = HotelMapper
                .toHotelRegisterResponse(
                        hotel,
                        city,
                        tokenResponse
                );
        System.out.println("response:  ");
        System.out.println(request.getEmail());
        this.hotelRepository.save(hotel);
        return response;
    }


}
