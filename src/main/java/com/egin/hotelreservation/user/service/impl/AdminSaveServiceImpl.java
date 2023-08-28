package com.egin.hotelreservation.user.service.impl;

import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.auth.service.AuthService;
import com.egin.hotelreservation.security.model.user.Role;
import com.egin.hotelreservation.user.dto.mappers.AdminMapper;
import com.egin.hotelreservation.user.dto.requests.adminRequests.AdminRegisterRequest;
import com.egin.hotelreservation.user.dto.responses.adminResponses.AdminRegisterResponse;
import com.egin.hotelreservation.user.model.Admin;
import com.egin.hotelreservation.user.repository.AdminRepository;
import com.egin.hotelreservation.user.service.AdminSaveService;
import com.egin.hotelreservation.user.service.rules.AdminBusinessRules;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Bir Admin entity nesnesinin register işlemlerini yapmak için kullanılan
 * Service katmanı class'ıdır.
 */
@Service
public class AdminSaveServiceImpl implements AdminSaveService {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final AdminBusinessRules adminBusinessRules;

    public AdminSaveServiceImpl(
            AuthService authService,
            PasswordEncoder passwordEncoder,
            AdminRepository adminRepository,
            AdminBusinessRules adminBusinessRules) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.adminBusinessRules = adminBusinessRules;
    }

    /**
     * Bir Admin Entity nesnesinin Register işlemlerini yapmak için kullanılan metottur.
     * @param adminRegisterRequest
     * @return
     */
    @Override
    public AdminRegisterResponse add(AdminRegisterRequest adminRegisterRequest) {

        adminBusinessRules.checkEmailExist(adminRegisterRequest.getEmail());
        final Admin admin = AdminMapper.adminRegisterRequestToAdmin(adminRegisterRequest);
        admin.setPassword(passwordEncoder.encode(adminRegisterRequest.getPassword()));
        admin.setRole(Role.ADMIN);
        admin.setActive(true);
        admin.setDeleted(false);

        final TokenResponse tokenResponse = this.authService.generateToken(admin);
        final AdminRegisterResponse response = AdminMapper.toAdminRegisterResponse(
                admin,
                tokenResponse
        );
        this.adminRepository.save(admin);
        return  response;

    }



}
