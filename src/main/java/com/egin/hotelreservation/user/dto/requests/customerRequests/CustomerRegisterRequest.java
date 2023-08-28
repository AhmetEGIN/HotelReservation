package com.egin.hotelreservation.user.dto.requests.customerRequests;

import com.egin.hotelreservation.common.annotations.validations.identityNumberValidation.IdentityNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Customer entity nesnesini oluşturmak için Controller katmanında istenecek olan
 * parametreleri içeren request DTO sınıfıdır.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterRequest {

    @NotNull
    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @IdentityNumber
    private String identityNumber;

    private LocalDateTime birthDate;


}
