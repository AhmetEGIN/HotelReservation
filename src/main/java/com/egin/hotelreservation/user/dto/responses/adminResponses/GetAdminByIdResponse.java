package com.egin.hotelreservation.user.dto.responses.adminResponses;

import com.egin.hotelreservation.user.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controller katmanında bir {@link Admin} entity'si Id parametresi ile çağırıldığında
 * geri dönülecek olan response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAdminByIdResponse {

    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private boolean isDeleted;

}
