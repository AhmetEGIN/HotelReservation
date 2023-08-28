package com.egin.hotelreservation.user.dto.requests.adminRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Admin entity nesnesini oluşturmak için Controller katmanında istenecek olan
 * parametreleri içeren request DTO sınıfıdır.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRegisterRequest {


    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

}
