package com.egin.hotelreservation.user.dto.responses.adminResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controller katmanında bir Admin entity'si email parametresine göre çağırıldığında
 * geri dönülecek olan response DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAdminByEmailResponse {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private boolean isDeleted;

}
