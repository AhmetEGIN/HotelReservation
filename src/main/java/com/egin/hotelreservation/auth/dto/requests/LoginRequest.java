package com.egin.hotelreservation.auth.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kullanıcıların sisteme giriş yapabilmesi için gerekli olan parametreleri
 * içeren request DTO sınıfıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

}
