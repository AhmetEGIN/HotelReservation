package com.egin.hotelreservation.invoice.service;

import com.egin.hotelreservation.invoice.model.Invoice;
import com.egin.hotelreservation.payment.model.eventModels.InvoiceEvent;

import java.util.List;

public interface InvoiceService {

    Invoice add(InvoiceEvent invoiceEvent);

    Invoice getInvoiceById(String id);
    List<Invoice> getAllInvoices();

}
