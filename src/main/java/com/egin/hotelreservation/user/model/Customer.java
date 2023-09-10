package com.egin.hotelreservation.user.model;

import com.egin.hotelreservation.roomBooking.model.RoomBooking;
import com.egin.hotelreservation.security.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Customer nesnesinin veri tabanına karşılık gelen Entity sınıfıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Customer extends User implements Serializable {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<RoomBooking> roomBookings;

}
