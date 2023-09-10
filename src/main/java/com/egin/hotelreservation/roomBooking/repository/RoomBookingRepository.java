package com.egin.hotelreservation.roomBooking.repository;

import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, String> {

//    Optional<RoomBooking> findRoomBookingByRoom_IdAndRoom_Active(String id, boolean status);


    Optional<RoomBooking> findRoomBookingByRoom_IdAndIsActive(String id, boolean status);
    Optional<List<RoomBooking>> findAllByRoom_Id(String id);
}
