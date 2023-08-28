package com.egin.hotelreservation.user.dto.responses.customerResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer nesnesi Active ve Id parametrelerine göre filtrelendiğinde Controller katmanında
 * geri dönülecek olan Response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetActiveCustomerByIdResponse {

    private String email;
    private String firstName;
    private String lastName;

}
