package com.egin.hotelreservation.hotel.service.rules;

import com.egin.hotelreservation.hotel.repository.HotelRepository;
import com.egin.hotelreservation.user.exception.EmailAlreadyExistException;
import org.springframework.stereotype.Component;

@Component
public class HotelBusinessRules {

    private final HotelRepository hotelRepository;


    public HotelBusinessRules(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void isEmailExist(String email) {
        if (this.hotelRepository.existsHotelByEmailEquals(email)){

            throw new EmailAlreadyExistException();
        }

    }


}
