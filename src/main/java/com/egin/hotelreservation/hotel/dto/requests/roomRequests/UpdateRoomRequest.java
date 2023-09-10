package com.egin.hotelreservation.hotel.dto.requests.roomRequests;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRoomRequest {

    private String description;

    private int personCapacity;

    @Min(value = 0)
    private double dailyPrice;

    private boolean hasTv;

    private boolean hasMinibar;

    private boolean hasKitchen;

}
