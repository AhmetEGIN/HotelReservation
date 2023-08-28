package com.egin.hotelreservation.user.controller;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.common.model.response.BaseResponse;
import com.egin.hotelreservation.user.dto.mappers.AdminMapper;
import com.egin.hotelreservation.user.dto.requests.adminRequests.AdminRegisterRequest;
import com.egin.hotelreservation.user.dto.requests.adminRequests.UpdateAdminRequest;
import com.egin.hotelreservation.user.dto.responses.adminResponses.AdminRegisterResponse;
import com.egin.hotelreservation.user.dto.responses.adminResponses.GetAdminByIdResponse;
import com.egin.hotelreservation.user.dto.responses.adminResponses.GetAllAdminsResponse;
import com.egin.hotelreservation.user.dto.responses.adminResponses.UpdateAdminResponse;
import com.egin.hotelreservation.user.model.Admin;
import com.egin.hotelreservation.user.service.AdminSaveService;
import com.egin.hotelreservation.user.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Admin Entity nesnesini dışarıya açtığımız Rest Controller class'ıdır.
 */
@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;
    private final AdminSaveService adminSaveService;

    public AdminController(
            AdminService adminService,
           AdminSaveService adminSaveService) {
        this.adminService = adminService;
        this.adminSaveService = adminSaveService;
    }


    /**
     * Dışarıdan Admin entity nesnesi için Register işlemi yapmak için
     * request gönderdiğimiz POST metodudur.
     * @param adminRegisterRequest
     * @return
     */
    @PostMapping
    public BaseResponse<AdminRegisterResponse> registerAdmin(@RequestBody @Valid AdminRegisterRequest adminRegisterRequest) {

        return BaseResponse.createdOf(this.adminSaveService.add(adminRegisterRequest));
    }

    /**
     * Admin Entity nesnesine login işlemi yapmak için kullanılacak POST metodudur.
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<TokenResponse> loginAdmin(@RequestBody @Valid LoginRequest loginRequest) {

        return BaseResponse.successOf(this.adminService.loginAdmin(loginRequest));
    }


    /**
     * Admin entity objesi üzerinde update işlemi yapmak için kullanılan PUT metodudur.
     * @param updateAdminRequest
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public BaseResponse<UpdateAdminResponse> update(@RequestBody @Valid UpdateAdminRequest updateAdminRequest) {
        Admin admin = this.adminService.update(updateAdminRequest);
        UpdateAdminResponse response = AdminMapper.adminToUpdateAdminResponse(admin);
        return BaseResponse.successOf(response);
    }


    /**
     * Admin entity nesnesi üzerinde delete işlemi yapmak için kullanılan DELETE metodudur.
     * @param id
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public BaseResponse delete(String id) {
        this.adminService.delete(id);
        return BaseResponse.NO_CONTENT;
    }

    /**
     * Admin entity nesnesi üzerinde soft delete işlemi yapmak için kullanılan DELETE metodudur.
     * @param isDeleted
     * @return
     */
    @DeleteMapping("/soft")
    public BaseResponse softDelete(@RequestParam boolean isDeleted) {
        this.adminService.softDelete(isDeleted);
        return BaseResponse.NO_CONTENT;
    }

    /**
     * Admin entity nesnesinin Active parametresini güncellemek için kullanılan PATCH metodudur.
     * @param status
     * @return
     */
    @PatchMapping
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public BaseResponse changeActiveState(@RequestParam boolean status) {

        this.adminService.changeActiveState(status);
        return BaseResponse.SUCCESS;
    }

    /**
     * Kayıtlı olan tüm Admin entity nesnelerini getirmek için kullanılan GET metodudur.
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public BaseResponse<List<GetAllAdminsResponse>> getAllAdmins() {
        List<GetAllAdminsResponse> response = this.adminService
                .getAllAdmins()
                .stream()
                .map(AdminMapper::adminToGetAllAdminsResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);

    }

    /**
     * Id değeri verilen Admin entity nesnesini getirmek için kullanılan GET metodudur
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResponse<GetAdminByIdResponse> getAdminById(@PathVariable("id") String id){
        GetAdminByIdResponse response = AdminMapper
                .adminToGetAdminByIdResponse(
                        this.adminService.getAdminById(id)
                );
        return BaseResponse.successOf(response);
    }



}
