package com.egin.hotelreservation.roomBooking.service.impl;

import com.egin.hotelreservation.common.helper.identity.UserInContext;
import com.egin.hotelreservation.hotel.model.Room;
import com.egin.hotelreservation.hotel.service.RoomService;
import com.egin.hotelreservation.roomBooking.dto.mappers.RoomBookingMapper;
import com.egin.hotelreservation.roomBooking.dto.requests.roomBookingRequests.CreateRoomBookingRequest;
import com.egin.hotelreservation.roomBooking.exception.RoomBookingNotFoundException;
import com.egin.hotelreservation.roomBooking.exception.RoomIsAlreadyBookedException;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import com.egin.hotelreservation.roomBooking.repository.RoomBookingRepository;
import com.egin.hotelreservation.roomBooking.service.RoomBookingService;
import com.egin.hotelreservation.user.model.Customer;
import com.egin.hotelreservation.user.service.CustomerService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {
    private final RoomBookingRepository roomBookingRepository;
    private final UserInContext userInContext;
    private final RoomService roomService;
    private final CustomerService customerService;


    public RoomBookingServiceImpl(
            RoomBookingRepository roomBookingRepository,
            UserInContext userInContext,
            RoomService roomService,
            CustomerService customerService
    ) {
        this.roomBookingRepository = roomBookingRepository;
        this.userInContext = userInContext;
        this.roomService = roomService;
        this.customerService = customerService;
    }


    private Customer getUser(){
        return this.customerService.getCustomerByEmail(this.userInContext.getUsername());
    }

    @Override
    public RoomBooking add(CreateRoomBookingRequest request) {

        this.checkRoomAvailable(request.getRoomId(), request.getStartDate(), request.getEndDate());
        Room room = this.roomService.getRoomById(request.getRoomId());

        RoomBooking booking = RoomBookingMapper.createRoomBookingRequestToRoomBooking(
                request,
                getTotalPrice(request.getRoomId(), request.getStartDate(), request.getEndDate()),
                room,
                getUser()
        );
        return this.roomBookingRepository.save(booking);

    }

    @Override
    public RoomBooking getRoomBookingById(String id) {

        return this.roomBookingRepository.findById(id)
                .orElseThrow(RoomBookingNotFoundException::new);
    }

    @Override
    public List<RoomBooking> getAllRoomBookings() {
        return this.roomBookingRepository.findAll();
    }

    @Override
    public List<RoomBooking> getAllRoomBookingsByCustomer() {

        return this.roomBookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getCustomer() == getUser())
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomBooking> getAllRoomBookingsByCustomerId(String id) {

        return this.roomBookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getCustomer().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRoomBooking(String id) {

        RoomBooking roomBooking = this.getRoomBookingById(id);
        this.roomBookingRepository.delete(roomBooking);
    }


    @Override
    public Double getTotalPrice(String roomId, LocalDateTime startDate, LocalDateTime endDate) {

        Room room = this.roomService.getRoomById(roomId);
        long duration = Duration.between(startDate, endDate).toDays();
        Double totalPrice = duration * room.getDailyPrice();

        return totalPrice;
    }

    @Override
    public void checkOut(String roomBookingId) {

        RoomBooking roomBooking = this.getRoomBookingById(roomBookingId);
        roomBooking.setActive(false);
        this.roomBookingRepository.save(roomBooking);
    }

    private void checkRoomAvailable(String roomId, LocalDateTime startDate, LocalDateTime endDate) {
        boolean status = true;
        Optional<List<RoomBooking>> roomBookings = this.roomBookingRepository
                .findAllByRoom_Id(roomId);

        if (roomBookings.isEmpty()) {
            return;
        }

        for (RoomBooking roomBooking : roomBookings.get()) {
            if ((startDate.isAfter(roomBooking.getStartDate()) && (startDate.isBefore(roomBooking.getEndDate())))
            || (endDate.isAfter(roomBooking.getStartDate()) && endDate.isBefore(roomBooking.getEndDate()))) {
                status = false;
            }

//            if ((startDate.isAfter(roomBooking.getEndDate()) &&endDate.isAfter(roomBooking.getEndDate()))
//                    || (startDate.isBefore(roomBooking.getStartDate()) && endDate.isBefore(roomBooking.getStartDate())) ) {
//                return;
//            }


        }

//        Optional<RoomBooking> roomBooking = this.roomBookingRepository.findRoomBookingByRoom_IdAndIsActive(roomId, true);
//        if (roomBooking.isEmpty()) {
//            return;
//        }
//        if ((startDate.isAfter(roomBooking.get().getEndDate()) &&endDate.isAfter(roomBooking.get().getEndDate()))
//                || (startDate.isBefore(roomBooking.get().getStartDate()) && endDate.isBefore(roomBooking.get().getStartDate())) ) {
//            return;
//        }
        if (!status) throw new RoomIsAlreadyBookedException();
    }
}
