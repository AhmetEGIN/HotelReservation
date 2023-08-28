package com.egin.hotelreservation.user.dto.responses.customerResponses;

import com.egin.hotelreservation.user.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controller katmanında bir {@link Customer} entity'si Id parametresi ile çağırıldığında
 * geri dönülecek olan response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCustomerByIdResponse {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private boolean isDeleted;

}
