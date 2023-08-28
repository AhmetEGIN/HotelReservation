package com.egin.hotelreservation.user.dto.responses.adminResponses;

import com.egin.hotelreservation.user.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Controller katmanında bir {@link Admin} nesnesi üzerinde Update işlemi yapıldığı zaman
 * geri dönülecek olan response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAdminResponse {

    private String firstName;
    private String lastName;
    private String email;
}
