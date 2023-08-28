package com.egin.hotelreservation.user.service;

import com.egin.hotelreservation.user.dto.requests.adminRequests.AdminRegisterRequest;
import com.egin.hotelreservation.user.dto.responses.adminResponses.AdminRegisterResponse;

public interface AdminSaveService {

    AdminRegisterResponse add(AdminRegisterRequest adminRegisterRequest);

}
