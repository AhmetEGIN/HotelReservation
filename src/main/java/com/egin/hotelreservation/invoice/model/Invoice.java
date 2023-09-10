package com.egin.hotelreservation.invoice.model;

import com.egin.hotelreservation.common.model.BaseEntity;
import com.egin.hotelreservation.payment.model.Payment;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "invoice_no", unique = true)
    private String invoiceNo;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "room_booking_id", referencedColumnName = "id")
    private RoomBooking roomBooking;

//    @OneToOne
//    @JoinColumn(name = "payment_id", referencedColumnName = "id")
//    private Payment payment;

}
