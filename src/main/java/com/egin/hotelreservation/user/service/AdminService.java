package com.egin.hotelreservation.user.service;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.user.dto.requests.adminRequests.UpdateAdminRequest;
import com.egin.hotelreservation.user.model.Admin;

import java.util.List;

/**
 * AdminServiceImpl nesnesini inject etmek için kullanılacak olan interface'dir.
 */
public interface AdminService {

    TokenResponse loginAdmin(LoginRequest loginRequest);
    Admin update(UpdateAdminRequest updateAdminRequest);
    void delete(String id);
    void softDelete(boolean isDeleted);
    List<Admin> getAllAdmins();
    Admin getAdminById(String id);
    void changeActiveState(boolean status);


}
