package com.egin.hotelreservation.hotel.model;

import com.egin.hotelreservation.city.model.City;
import com.egin.hotelreservation.hotel.model.enums.HotelStatus;
import com.egin.hotelreservation.hotel.model.enums.HotelType;
import com.egin.hotelreservation.security.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Veri tabanında hotels isimli tabloya denk gelen Entity class'ıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "hotels")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Hotel extends User {


    @Column(name = "name")
    private String name;

    @Column(name = "star")
    private int star;

    @Column(name = "hotel_type")
    @Enumerated(EnumType.STRING)
    private HotelType hotelType;


    @Column(name = "hotel_status")
    @Enumerated(EnumType.STRING)
    private HotelStatus hotelStatus;



    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

}
