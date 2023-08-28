package com.egin.hotelreservation.user.dto.responses.adminResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Controller katmanında bir Admin nesnesinin register işlemi gerçekleştiğinde
 * geriye dönülecek olan Response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRegisterResponse {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String accessToken;
    private String refreshToken;

}
