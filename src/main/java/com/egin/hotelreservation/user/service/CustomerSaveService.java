package com.egin.hotelreservation.user.service;

import com.egin.hotelreservation.user.dto.requests.customerRequests.CustomerRegisterRequest;
import com.egin.hotelreservation.user.dto.responses.customerResponses.CustomerRegisterResponse;

public interface CustomerSaveService {

    CustomerRegisterResponse add(CustomerRegisterRequest customerRegisterRequest);

}
