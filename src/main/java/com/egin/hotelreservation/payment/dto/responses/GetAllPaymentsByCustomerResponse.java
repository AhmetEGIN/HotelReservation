package com.egin.hotelreservation.payment.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllPaymentsByCustomerResponse {

    private String roomBookingId;
    private String customerFirstName;
    private String customerLastName;
    private String totalPrice;
    private LocalDateTime createdDate;


}
