package com.egin.hotelreservation.user.dto.responses.customerResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controller katmanında bir Customer entity'si email parametresine göre çağırıldığında
 * geri dönülecek olan response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCustomerByEmailResponse {

    private String id;
    private String email;
    private String firstName;
    private String lastName;

}
