package com.egin.hotelreservation.invoice.service.impl;

import com.egin.hotelreservation.invoice.exception.InvoiceNotFoundException;
import com.egin.hotelreservation.invoice.model.Invoice;
import com.egin.hotelreservation.invoice.repository.InvoiceRepository;
import com.egin.hotelreservation.invoice.service.InvoiceService;
import com.egin.hotelreservation.payment.model.eventModels.InvoiceEvent;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import com.egin.hotelreservation.roomBooking.service.RoomBookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final RoomBookingService roomBookingService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, RoomBookingService roomBookingService) {
        this.invoiceRepository = invoiceRepository;
        this.roomBookingService = roomBookingService;
    }

    @Override
    public Invoice add(InvoiceEvent invoiceEvent) {

        Invoice invoice = Invoice.builder()
                .invoiceNo(createInvoiceNo())
                .roomBooking(getRoomBookingById(invoiceEvent.getRoomBookingId()))
                .createdDate(LocalDateTime.now())
                .build();

        return this.invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(String id) {

        return this.invoiceRepository.findById(id)
                .orElseThrow(InvoiceNotFoundException::new);
    }

    @Override
    public List<Invoice> getAllInvoices() {

        return this.invoiceRepository.findAll();
    }


    private String createInvoiceNo() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();

    }

    private RoomBooking getRoomBookingById(String id) {

        return this.roomBookingService.getRoomBookingById(id);
    }


}
