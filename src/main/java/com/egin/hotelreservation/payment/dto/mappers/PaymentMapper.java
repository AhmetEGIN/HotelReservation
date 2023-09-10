package com.egin.hotelreservation.payment.dto.mappers;

import com.egin.hotelreservation.payment.dto.responses.CreatePaymentResponse;
import com.egin.hotelreservation.payment.dto.responses.GetAllPaymentsByCustomerResponse;
import com.egin.hotelreservation.payment.dto.responses.GetAllPaymentsResponse;
import com.egin.hotelreservation.payment.dto.responses.GetPaymentByIdResponse;
import com.egin.hotelreservation.payment.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public static CreatePaymentResponse paymentToCreatePaymentResponse(Payment payment) {

        return CreatePaymentResponse.builder()
                .roomBookingId(payment.getRoomBooking().getId())
                .customerFirstName(payment.getRoomBooking().getCustomer().getFirstName())
                .customerLastName(payment.getRoomBooking().getCustomer().getLastName())
                .createdDate(payment.getCreatedDate())
                .build();
    }


    public static GetAllPaymentsResponse paymentToGetAllPaymentsResponse(Payment payment) {

        return GetAllPaymentsResponse.builder()
                .roomBookingId(payment.getRoomBooking().getId())
                .customerFirstName(payment.getRoomBooking().getCustomer().getFirstName())
                .customerLastName(payment.getRoomBooking().getCustomer().getLastName())
                .createdDate(payment.getCreatedDate())
                .build();
    }


    public static GetAllPaymentsByCustomerResponse paymentToGetAllPaymentsByCustomerResponse(Payment payment) {

        return GetAllPaymentsByCustomerResponse.builder()
                .roomBookingId(payment.getRoomBooking().getId())
                .customerFirstName(payment.getRoomBooking().getCustomer().getFirstName())
                .customerLastName(payment.getRoomBooking().getCustomer().getLastName())
                .createdDate(payment.getCreatedDate())
                .build();
    }

    public static GetPaymentByIdResponse paymentToGetPaymentByIdResponse(Payment payment) {

        return GetPaymentByIdResponse.builder()
                .roomBookingId(payment.getRoomBooking().getId())
                .customerFirstName(payment.getRoomBooking().getCustomer().getFirstName())
                .customerLastName(payment.getRoomBooking().getCustomer().getLastName())
                .createdDate(payment.getCreatedDate())
                .build();
    }

}
