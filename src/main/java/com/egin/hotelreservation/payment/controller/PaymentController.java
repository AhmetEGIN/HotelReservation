package com.egin.hotelreservation.payment.controller;

import com.egin.hotelreservation.common.model.response.BaseResponse;
import com.egin.hotelreservation.payment.dto.mappers.PaymentMapper;
import com.egin.hotelreservation.payment.dto.requests.CreatePaymentRequest;
import com.egin.hotelreservation.payment.dto.responses.CreatePaymentResponse;
import com.egin.hotelreservation.payment.dto.responses.GetAllPaymentsByCustomerResponse;
import com.egin.hotelreservation.payment.dto.responses.GetAllPaymentsResponse;
import com.egin.hotelreservation.payment.dto.responses.GetPaymentByIdResponse;
import com.egin.hotelreservation.payment.model.Payment;
import com.egin.hotelreservation.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('customer:create', 'admin:create', 'hotel:create')")
    public BaseResponse<CreatePaymentResponse> payWithCreditCard(@RequestBody @Valid CreatePaymentRequest request) {

        final Payment payment = this.paymentService.payWithCreditCard(request);
        final CreatePaymentResponse response = PaymentMapper.paymentToCreatePaymentResponse(payment);

        return BaseResponse.createdOf(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('customer:read', 'admin:read', 'hotel:read')")
    public BaseResponse<GetPaymentByIdResponse> getPaymentById(@PathVariable("id") String id) {

        final Payment payment = this.paymentService.getPaymentById(id);
        final GetPaymentByIdResponse response = PaymentMapper.paymentToGetPaymentByIdResponse(payment);

        return BaseResponse.successOf(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('admin:read', 'hotel:read')")
    public BaseResponse<List<GetAllPaymentsResponse>> getAllPayments() {

        List<Payment> payments = this.paymentService.getAllPayments();
        List<GetAllPaymentsResponse> response = payments.stream()
                .map(PaymentMapper::paymentToGetAllPaymentsResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);

    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('customer:read')")
    public BaseResponse<List<GetAllPaymentsByCustomerResponse>> getAllPaymentsByCustomer() {

        final List<Payment> payments = this.paymentService.getAllPaymentsByCustomer();
        final List<GetAllPaymentsByCustomerResponse> response = payments.stream()
                .map(PaymentMapper::paymentToGetAllPaymentsByCustomerResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);
    }

}
