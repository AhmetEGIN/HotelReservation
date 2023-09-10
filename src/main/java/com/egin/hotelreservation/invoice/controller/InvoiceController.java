package com.egin.hotelreservation.invoice.controller;

import com.egin.hotelreservation.common.model.response.BaseResponse;
import com.egin.hotelreservation.invoice.dto.mappers.InvoiceMapper;
import com.egin.hotelreservation.invoice.dto.responses.GetAllInvoicesResponse;
import com.egin.hotelreservation.invoice.dto.responses.GetInvoiceByIdResponse;
import com.egin.hotelreservation.invoice.model.Invoice;
import com.egin.hotelreservation.invoice.service.InvoiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

//    Invoice add(Payment payment);

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('customer:read', 'admin:read', 'hotel:read')")
    public BaseResponse<GetInvoiceByIdResponse> getInvoiceById(@PathVariable("id") String id) {

        Invoice invoice = this.invoiceService.getInvoiceById(id);
        GetInvoiceByIdResponse response = InvoiceMapper.invoiceToGetInvoiceByIdResponse(invoice);

        return BaseResponse.successOf(response);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('hotel:read', 'admin:read')")
    public BaseResponse<List<GetAllInvoicesResponse>> getAllInvoices() {

        List<GetAllInvoicesResponse> response = this.invoiceService
                .getAllInvoices()
                .stream()
                .map(InvoiceMapper::invoiceToGetAllInvoicesResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);

    }


}
