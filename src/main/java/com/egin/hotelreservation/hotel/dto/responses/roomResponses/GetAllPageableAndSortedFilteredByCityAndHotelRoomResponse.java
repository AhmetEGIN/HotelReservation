package com.egin.hotelreservation.hotel.dto.responses.roomResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllPageableAndSortedFilteredByCityAndHotelRoomResponse {

    private String id;
    private String description;
    private int floor;
    private int personCapacity;
    private double dailyPrice;
    private boolean hasTv;
    private boolean hasMinibar;
    private boolean hasKitchen;
    private String hotelName;
    private boolean isActive;
    private boolean isDeleted;


}
