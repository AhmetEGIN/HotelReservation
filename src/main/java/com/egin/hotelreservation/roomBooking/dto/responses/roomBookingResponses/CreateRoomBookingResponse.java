package com.egin.hotelreservation.roomBooking.dto.responses.roomBookingResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoomBookingResponse {

    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private String customerFirstName;
    private String customerLastName;
    private String roomId;
    private String hotelName;

}
