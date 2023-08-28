package com.egin.hotelreservation.user.dto.mappers;

import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.user.dto.requests.adminRequests.AdminRegisterRequest;
import com.egin.hotelreservation.user.dto.responses.adminResponses.*;
import com.egin.hotelreservation.user.model.Admin;
import org.springframework.stereotype.Component;

/**
 * Admin entity nesneleri ile ilgili Controller katmanında alınan veya verilen class' ların
 * map işlemini gerçekleştirecek olan sınıftır.
 */
@Component
public class AdminMapper {

    /**
     * Controller'dan Admin Entity'si oluşturmak için gönderilen Request objesini
     * Admin nesnesine map'ler.
     * @param request
     * @return
     */
    public static Admin adminRegisterRequestToAdmin(AdminRegisterRequest request) {
        return Admin.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }


    /**
     * Controller katmanında bir Admin Register işlemi yapıldığında {@link Admin} ve {@link TokenResponse} nesnesi
     * alarak {@link AdminRegisterResponse} nesnesine map'ler.
     * @param admin
     * @param tokenResponse
     * @return
     */
    public static AdminRegisterResponse toAdminRegisterResponse(
            Admin admin,
            TokenResponse tokenResponse
    ) {
        return AdminRegisterResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();

    }

    /**
     * Controller katmanında tekil bir Admin Entity nesnesi çağırıldığında Admin
     * nesnesini GetCustomerResponse nesnesine map'ler.
     * @param admin
     * @return
     */

    public static GetAdminByIdResponse adminToGetAdminByIdResponse(Admin admin) {

        return GetAdminByIdResponse.builder()
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .isActive(admin.isActive())
                .isDeleted(admin.isDeleted())
                .build();
    }

    /**
     * Controller katmanında bir Admin Entity nesnesi Email parametresine göre çağırıldığında Admin objesini
     * {@link GetAdminByEmailResponse} nesnesine map'ler.
     * @param admin
     * @return
     */
    public static GetAdminByEmailResponse adminToGetAdminByEmailResponse(Admin admin){

        return GetAdminByEmailResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .isActive(admin.isActive())
                .isDeleted(admin.isDeleted())
                .build();
    }


    /**
     * Controller katmanında tüm Admin Entity'leri çağırıldığında {@link Admin} nesnesini alarak
     * {@link GetAllAdminsResponse} nesnesine map'ler.
     * @param admin
     * @return
     */
    public static GetAllAdminsResponse adminToGetAllAdminsResponse(Admin admin) {

        return GetAllAdminsResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .isActive(admin.isActive())
                .isDeleted(admin.isDeleted())
                .build();
    }

    /**
     * Controller katmanında bir {@link Admin} nesnesi üzerinde Update işlemi yapıldığında
     * {@link Admin} nesnesini {@link UpdateAdminResponse} nesnesine map'ler.
     * @param admin
     * @return
     */

    public static UpdateAdminResponse adminToUpdateAdminResponse(Admin admin) {

        return UpdateAdminResponse.builder()
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .build();
    }


}
