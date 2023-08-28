package com.egin.hotelreservation.user.dto.requests.adminRequests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Kayıtlı bir Admin nesnesinin bilgilerini güncellemek için Controller katmanında
 * istenecek parametreleri içeren Request DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAdminRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

}
