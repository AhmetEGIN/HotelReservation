package com.egin.hotelreservation.invoice.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInvoiceResponse {

    private String id;
    private String invoiceNo;
    private Double totalPrice;
    private String customerFirstName;
    private String customerLastName;
    private String roomBookingId;
    private String paymentId;
    private LocalDateTime createdDate;



}
