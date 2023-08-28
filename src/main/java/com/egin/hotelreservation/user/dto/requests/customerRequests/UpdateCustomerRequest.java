package com.egin.hotelreservation.user.dto.requests.customerRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kayıtlı bir Customer nesnesinin bilgilerini güncellemek için Controller katmanında
 * istenecek parametreleri içeren Request DTO nesnesidir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerRequest {

    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

}
