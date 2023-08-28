package com.egin.hotelreservation.hotel.repository;

import com.egin.hotelreservation.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    boolean existsHotelByEmailEquals(String email);

    Optional<Hotel> findByEmail(String email);
    Hotel findHotelByEmail(String email);


}
