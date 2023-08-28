package com.egin.hotelreservation.user.service.impl;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.auth.service.AuthService;
import com.egin.hotelreservation.common.helper.identity.UserInContext;
import com.egin.hotelreservation.user.dto.requests.adminRequests.UpdateAdminRequest;
import com.egin.hotelreservation.user.exception.AdminNotFoundException;
import com.egin.hotelreservation.user.model.Admin;
import com.egin.hotelreservation.user.repository.AdminRepository;
import com.egin.hotelreservation.user.service.AdminService;
import com.egin.hotelreservation.user.service.rules.AdminBusinessRules;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Admin Entity nesnesi üzerinde işlem yapmayı sağlayacak Service katmanı class'ıdır.
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserInContext userInContext;
    private final AuthService authService;
    private final AdminBusinessRules adminBusinessRules;

    public AdminServiceImpl(AdminRepository adminRepository,
                            UserInContext userInContext,
                            AuthService authService,
                            AdminBusinessRules adminBusinessRules
    ) {
        this.adminRepository = adminRepository;
        this.userInContext = userInContext;
        this.authService = authService;
        this.adminBusinessRules = adminBusinessRules;
    }


    /**
     * Authenticate olmuş kullanıcıya SecurityContext üzerinden ulaşmayı
     * sağlayacak olan metottur.
     * @return
     */
    private Admin getUser() {

        return this.adminRepository
                .findByEmail(this.userInContext.getUsername())
                .orElseThrow(AdminNotFoundException::new);
    }

    /**
     * Admin Entity nesnesinin sistemde Login işlemlerini yapmasını sağlayacak olan metottur
     * @param loginRequest
     * @return
     */
    @Override
    public TokenResponse loginAdmin(LoginRequest loginRequest) {

        return this.authService.authenticate(loginRequest);
    }

    /**
     * Sistemde kayıtlı olan bir {@link Admin} Entity nesnesin güncellenmye izin verilen bilgilerini
     * güncellemek için kullanılan Service katmanı metodudur.
     * @param updateAdminRequest
     * @return Admin
     */
    @Override
    public Admin update(UpdateAdminRequest updateAdminRequest) {

//        final Admin admin = getUser();
        System.out.println(getUser().getFirstName());
        getUser().setFirstName(updateAdminRequest.getFirstName());
        getUser().setLastName(updateAdminRequest.getLastName());
        Admin admin = this.adminRepository.save(getUser());
        System.out.println("*************");
        System.out.println(admin.getFirstName());
        return getUser();
    }

    /**
     * Id değeri verilen bir {@link Admin} entity nesnesini silmek için kullanılan Service katmanı metodudur.
     * @param id
     */
    @Override
    public void delete(String id) {

        final Admin admin = getAdminById(id);
        this.adminRepository.delete(admin);
    }

    /**
     * Sistemde Authenticate olmuş bir {@link Admin} entity nesnesi üzerinde soft delete
     * işlemi yapmak için kullanılan Service metodudur.
     * @param isDeleted
     */
    @Override
    public void softDelete(boolean isDeleted) {

        this.getUser().setDeleted(isDeleted);
        this.adminRepository.save(getUser());
    }

    /**
     * Sistemde bulunan tüm {@link Admin} entity nesnelerini getirmek için kullanılan Service katmanı metodudur.
     *
     * @return List<Admin>
     */
    @Override
    public List<Admin> getAllAdmins() {

        return this.adminRepository.findAll();
    }

    /**
     * Id değeri verilen {@link Admin} entity nesnesini getirmek için kullanılan Service katmanı metodudur.
     * @param id
     * @return
     */
    @Override
    public Admin getAdminById(String id) {

        return this.adminRepository
                .findById(id)
                .orElseThrow(AdminNotFoundException::new);
    }

    /**
     * Sistemde Authenticate olmuş bir {@link Admin} entity nesnesinin Active parametresini güncellemek
     * için kullanılan Service katmanı metodudur.
     * @param status
     */
    @Override
    public void changeActiveState(boolean status) {
        getUser().setActive(status);
        this.adminRepository.save(getUser());

    }
}
