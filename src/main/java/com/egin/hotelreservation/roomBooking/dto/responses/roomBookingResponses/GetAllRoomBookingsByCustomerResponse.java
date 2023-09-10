package com.egin.hotelreservation.roomBooking.dto.responses.roomBookingResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllRoomBookingsByCustomerResponse {


    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private String customerFirstName;
    private String customerLastName;
    private String roomId;
    private String hotelName;
    private boolean isDeleted;
    private boolean isActive;

}
