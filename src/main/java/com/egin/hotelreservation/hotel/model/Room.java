package com.egin.hotelreservation.hotel.model;

import com.egin.hotelreservation.common.model.BaseEntity;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "floor")
    private int floor;

    @Column(name = "person_capacity")
    private int personCapacity;

    @Column(name = "daily_price")
    private double dailyPrice;

    @Column(name = "has_tv")
    private boolean hasTv;

    @Column(name = "has_minibar")
    private boolean hasMinibar;

    @Column(name = "has_kitchen")
    private boolean hasKitchen;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
    private List<RoomBooking> roomBookings;



}
