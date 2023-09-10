package com.egin.hotelreservation.roomBooking.dto.requests.roomBookingRequests;

import com.egin.hotelreservation.common.annotations.validations.notPasteDateValidation.NotPasteDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRoomBookingRequest {

    @NotPasteDate
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    private String roomId;



}
