package com.egin.hotelreservation.roomBooking.model;

import com.egin.hotelreservation.common.model.BaseEntity;
import com.egin.hotelreservation.hotel.model.Room;
import com.egin.hotelreservation.invoice.model.Invoice;
import com.egin.hotelreservation.payment.model.Payment;
import com.egin.hotelreservation.user.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.authentication.CachingUserDetailsService;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "room_bookings")
public class RoomBooking extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price")
    private Double price;


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;


}
