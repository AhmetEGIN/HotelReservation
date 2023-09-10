package com.egin.hotelreservation.user.service;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.user.dto.requests.customerRequests.CustomerRegisterRequest;
import com.egin.hotelreservation.user.dto.requests.customerRequests.UpdateCustomerRequest;
import com.egin.hotelreservation.user.model.Customer;

import java.util.List;

/**
 * CustomerService nesnesini inject etmek için kullanılacak interface'dir.
 */
public interface CustomerService {


    TokenResponse loginCustomer(LoginRequest customerLoginRequest);
    Customer update(UpdateCustomerRequest updateCustomerRequest);

    void delete(String id);
    void softDelete(boolean isDeleted);
    List<Customer> getAllCustomers();
    Customer getCustomerById(String id);
    void changeActiveState(boolean status);

    Customer getCustomerByEmail(String username);
}
