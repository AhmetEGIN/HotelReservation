package com.egin.hotelreservation.user.dto.responses.customerResponses;

import com.egin.hotelreservation.user.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controller katmanında bir {@link Customer} nesnesi üzerinde Update işlemi yapıldığı zaman
 * geri dönülecek olan response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerResponse {

    private String id;
    private String email;
    private String firstName;
    private String lastName;

}
