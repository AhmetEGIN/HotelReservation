package com.egin.hotelreservation.auth.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kullanıcılara bir token bilgisi verilmesi gerektiğinde verilecek olan
 * parametreleri içeren response DTO sınıfıdır.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {

    String accessToken;
    String refreshToken;

}
