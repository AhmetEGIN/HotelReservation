package com.egin.hotelreservation.user.model;

import com.egin.hotelreservation.security.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Admin kullanıcısının veri tabanına karşılık gelen Entity sınıfıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Admin extends User {



    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
