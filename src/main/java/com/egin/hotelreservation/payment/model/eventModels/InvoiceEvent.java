package com.egin.hotelreservation.payment.model.eventModels;

import com.egin.hotelreservation.payment.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceEvent implements Serializable {

    private Double totalPrice;
    private String roomBookingId;
    private String roomId;
    private LocalDateTime createdDate;
    private String customerFirstName;
    private String customerLastName;
    private PaymentType paymentType;
//    private LocalDateTime time;
//    private RoomBooking roomBooking;




}
