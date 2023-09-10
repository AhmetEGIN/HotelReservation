package com.egin.hotelreservation.invoice.event;

import com.egin.hotelreservation.invoice.service.InvoiceService;
import com.egin.hotelreservation.payment.model.eventModels.InvoiceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class InvoiceConsumer {

    private final InvoiceService invoiceService;

    public InvoiceConsumer(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void consume(InvoiceEvent invoiceEvent) {
        this.invoiceService.add(invoiceEvent);
//        System.out.println(invoiceEvent.getTime());
//        log.info("Rechnung wurde erstellt: ");
        System.out.println("Fatura oluştu");
    }


}
