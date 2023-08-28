package com.egin.hotelreservation.user.dto.responses.customerResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controller katmanında Customer entity'lerinin tamamı istendiğinde geri dönülecek
 * olan response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllCustomersResponse {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private boolean isDeleted;

}
