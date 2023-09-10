package com.egin.hotelreservation.payment.model;

import com.egin.hotelreservation.common.model.BaseEntity;
import com.egin.hotelreservation.invoice.model.Invoice;
import com.egin.hotelreservation.payment.model.enums.PaymentType;
import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @OneToOne(mappedBy = "payment")
    private RoomBooking roomBooking;

//    @OneToOne(mappedBy = "payment")
//    private Invoice invoice;


}
