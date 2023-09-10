package com.egin.hotelreservation.payment.service.impl;

import com.egin.hotelreservation.common.helper.identity.UserInContext;
import com.egin.hotelreservation.payment.dto.requests.CreatePaymentRequest;
import com.egin.hotelreservation.payment.exception.PaymentNotFoundException;
import com.egin.hotelreservation.payment.model.Payment;
import com.egin.hotelreservation.payment.model.enums.PaymentType;
import com.egin.hotelreservation.payment.model.eventModels.InvoiceEvent;
import com.egin.hotelreservation.payment.repository.PaymentRepository;
import com.egin.hotelreservation.payment.service.PaymentService;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import com.egin.hotelreservation.roomBooking.service.RoomBookingService;
import com.egin.hotelreservation.user.model.Customer;
import com.egin.hotelreservation.user.service.CustomerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerService customerService;
    private final RoomBookingService roomBookingService;
    private final UserInContext userInContext;
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            CustomerService customerService,
            RoomBookingService roomBookingService,
            UserInContext userInContext,
            RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.customerService = customerService;
        this.roomBookingService = roomBookingService;
        this.userInContext = userInContext;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Payment payWithCreditCard(CreatePaymentRequest request) {

        final RoomBooking roomBooking = this.roomBookingService.getRoomBookingById(request.getRoomBookingId());
        Payment payment = Payment.builder()
                .paymentType(PaymentType.CREDIT_CARD)
                .roomBooking(roomBooking)
                .createdDate(LocalDateTime.now())
                .build();

        final Payment savedPayment = this.paymentRepository.save(payment);
        final InvoiceEvent invoiceEvent = createInvoiceEvent(payment);
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, invoiceEvent);

        return savedPayment;
    }

    @Override
    public Payment getPaymentById(String id) {

        return this.paymentRepository
                .findById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

    @Override
    public List<Payment> getAllPayments() {

        return this.paymentRepository.findAll();
    }

    @Override
    public List<Payment> getAllPaymentsByCustomer() {

        final Customer customer = this.customerService.getCustomerByEmail(userInContext.getUsername());
        List<Payment> response = getAllPayments().stream()
                .filter(payment -> payment.getRoomBooking().getCustomer().getEmail().equals(customer.getEmail()))
                .collect(Collectors.toList());

        return response;
    }

    private InvoiceEvent createInvoiceEvent(Payment payment) {

        return InvoiceEvent.builder()
                .totalPrice(payment.getRoomBooking().getPrice())
                .paymentType(payment.getPaymentType())
                .roomBookingId(payment.getRoomBooking().getId())
                .roomId(payment.getRoomBooking().getRoom().getId())
                .createdDate(LocalDateTime.now())
                .customerFirstName(payment.getRoomBooking().getCustomer().getFirstName())
                .customerLastName(payment.getRoomBooking().getCustomer().getLastName())
                .build();
    }


}
