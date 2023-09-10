package com.egin.hotelreservation.payment.service;

import com.egin.hotelreservation.payment.dto.requests.CreatePaymentRequest;
import com.egin.hotelreservation.payment.model.Payment;

import java.util.List;

public interface PaymentService {

    Payment payWithCreditCard(CreatePaymentRequest request);
    Payment getPaymentById(String id);
    List<Payment> getAllPayments();
    List<Payment> getAllPaymentsByCustomer();


}
