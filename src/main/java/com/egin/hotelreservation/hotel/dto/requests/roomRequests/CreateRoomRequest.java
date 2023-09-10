package com.egin.hotelreservation.hotel.dto.requests.roomRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoomRequest {

    @NotNull
    private String description;

    @NotNull
    private int floor;

    @NotNull
    private int personCapacity;

    @NotNull
    @Min(value = 0)
    private double dailyPrice;

    private boolean hasTv;

    private boolean hasMinibar;

    private boolean hasKitchen;

}
