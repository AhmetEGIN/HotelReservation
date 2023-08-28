package com.egin.hotelreservation.user.dto.mappers;

import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.user.dto.requests.customerRequests.CustomerRegisterRequest;
import com.egin.hotelreservation.user.dto.responses.customerResponses.*;
import com.egin.hotelreservation.user.model.Customer;
import org.springframework.stereotype.Component;

/**
 * Customer entity nesneleri ile ilgili Controller katmanında alınan veya verilen class' ların
 * map işlemini gerçekleştirecek olan sınıftır.
 */
@Component
public class CustomerMapper {

    /**
     * Controller'dan Customer Entity'si oluşturmak için gönderilen Request objesini
     * Customer nesnesine map'ler.
     * @param request
     * @return Customer
     */
    public static Customer customerRegisterRequestToCustomer(CustomerRegisterRequest request) {

        return Customer.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .identityNumber(request.getIdentityNumber())
                .birthDate(request.getBirthDate())
                .build();

    }


    /**
     * Bir Customer Entity'si sistemde register işlemi yaptığında Controller katmanında gelen Customer
     * nesnesini geri döndürülecek olan CustomerRegisterResponse nesnesine map'ler.
     * @param customer
     * @param tokenResponse
     * @return
     */
    public static CustomerRegisterResponse toCustomerRegisterResponse(
            Customer customer,
            TokenResponse tokenResponse
    ) {
        CustomerRegisterResponse response = CustomerRegisterResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();

        return response;

    }

    /**
     * Controller katmanında tekil bir Customer Entity nesnesi çağırıldığında Customer
     * nesnesini GetCustomerResponse nesnesine map'ler.
     * @param customer
     * @return
     */
    public static GetCustomerByIdResponse customerToGetCustomerByIdResponse(Customer customer) {

        return GetCustomerByIdResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .isActive(customer.isActive())
                .isDeleted(customer.isDeleted())
                .build();
    }


    /**
     * Controller katmanında bir Customer Entity'si Email parametresine göre çağırıldığında Customer nesnesini
     * GetCustomerByEmailResponse nesnesine map'ler.
     * @param customer
     * @return
     */
    public static GetCustomerByEmailResponse customerToGetCustomerByEmailResponse(Customer customer) {

        return GetCustomerByEmailResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }


    /**
     * Controller katmanında bir Customer nesnesi Id ve Active parametresine göre çağırıldığında
     * Customer nesnesini GetActiveCustomerByIdResponse nesnesine map'ler.
     * @param customer
     * @return
     */
    public static GetActiveCustomerByIdResponse customerToGetActiveCustomerByIdResponse(Customer customer) {

        return GetActiveCustomerByIdResponse.builder()
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }


    /**
     * Controller katmanında tüm Customer Entity'leri çağırıldığında Customer nesnelerini
     * GetAllCustomersResponse nesnesine map'ler.
     * @param customer
     * @return
     */
    public static GetAllCustomersResponse customerToGetAllCustomersResponse(Customer customer) {

        return GetAllCustomersResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .isActive(customer.isActive())
                .isDeleted(customer.isDeleted())
                .build();
    }


    /**
     * Bir Customer nesnesi güncellendiği zaman gelen Customer nesnesini
     * UpdateCustomerResponse nesnesine map'ler.
     * @param customer
     * @return
     */
    public static UpdateCustomerResponse customerToUpdateCustomerResponse(Customer customer) {

        return UpdateCustomerResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }



}
