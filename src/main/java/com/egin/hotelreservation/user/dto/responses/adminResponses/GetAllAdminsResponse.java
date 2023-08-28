package com.egin.hotelreservation.user.dto.responses.adminResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Controller katmanında Admin entity'lerinin tamamı istendiğinde geri dönülecek
 * olan response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllAdminsResponse {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private boolean isDeleted;

}
