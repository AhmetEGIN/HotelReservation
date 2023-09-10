package com.egin.hotelreservation.invoice.dto.mappers;

import com.egin.hotelreservation.invoice.dto.responses.CreateInvoiceResponse;
import com.egin.hotelreservation.invoice.dto.responses.GetAllInvoicesResponse;
import com.egin.hotelreservation.invoice.dto.responses.GetInvoiceByIdResponse;
import com.egin.hotelreservation.invoice.model.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public static CreateInvoiceResponse invoiceToCreateInvoiceResponse(Invoice invoice) {

        return CreateInvoiceResponse.builder()
                .id(invoice.getId())
                .invoiceNo(invoice.getInvoiceNo())
                .totalPrice(invoice.getTotalPrice())
                .createdDate(invoice.getCreatedDate())
                .paymentId(invoice.getRoomBooking().getPayment().getId())
                .customerFirstName(invoice.getRoomBooking().getCustomer().getFirstName())
                .customerLastName(invoice.getRoomBooking().getCustomer().getLastName())
                .roomBookingId(invoice.getRoomBooking().getId())
                .build();
    }


    public static GetAllInvoicesResponse invoiceToGetAllInvoicesResponse(Invoice invoice) {

        return GetAllInvoicesResponse.builder()
                .id(invoice.getId())
                .invoiceNo(invoice.getInvoiceNo())
                .totalPrice(invoice.getTotalPrice())
                .createdDate(invoice.getCreatedDate())
                .paymentId(invoice.getRoomBooking().getPayment().getId())
                .customerFirstName(invoice.getRoomBooking().getCustomer().getFirstName())
                .customerLastName(invoice.getRoomBooking().getCustomer().getLastName())
                .roomBookingId(invoice.getRoomBooking().getId())
                .build();
    }

    public static GetInvoiceByIdResponse invoiceToGetInvoiceByIdResponse(Invoice invoice) {

        return GetInvoiceByIdResponse.builder()
                .id(invoice.getId())
                .invoiceNo(invoice.getInvoiceNo())
                .totalPrice(invoice.getTotalPrice())
                .createdDate(invoice.getCreatedDate())
                .paymentId(invoice.getRoomBooking().getPayment().getId())
                .customerFirstName(invoice.getRoomBooking().getCustomer().getFirstName())
                .customerLastName(invoice.getRoomBooking().getCustomer().getLastName())
                .roomBookingId(invoice.getRoomBooking().getId())
                .build();
    }

}
