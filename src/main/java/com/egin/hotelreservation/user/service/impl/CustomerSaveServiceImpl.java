package com.egin.hotelreservation.user.service.impl;

import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.auth.service.AuthService;
import com.egin.hotelreservation.security.model.user.Role;
import com.egin.hotelreservation.user.dto.mappers.CustomerMapper;
import com.egin.hotelreservation.user.dto.requests.customerRequests.CustomerRegisterRequest;
import com.egin.hotelreservation.user.dto.responses.customerResponses.CustomerRegisterResponse;
import com.egin.hotelreservation.user.model.Customer;
import com.egin.hotelreservation.user.repository.CustomerRepository;
import com.egin.hotelreservation.user.service.CustomerSaveService;
import com.egin.hotelreservation.user.service.rules.CustomerBusinessRules;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Bir Customer Entity nesnesi kaydetmek için kullanılan Service katmanı class'ıdır.
 */
@Service
public class CustomerSaveServiceImpl implements CustomerSaveService {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;

    public CustomerSaveServiceImpl(
            AuthService authService,
            PasswordEncoder passwordEncoder,
            CustomerRepository customerRepository,
            CustomerBusinessRules customerBusinessRules) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.customerBusinessRules = customerBusinessRules;
    }

    /**
     * Customer nesnesinin Register işlemlerini yapmak için kullanılan metottur.
     * @param customerRegisterRequest
     * @return CustomerRegisterResponse
     */
    @Override
    public CustomerRegisterResponse add(CustomerRegisterRequest customerRegisterRequest) {

        customerBusinessRules.checkEmailExist(customerRegisterRequest.getEmail());
        final Customer customer = CustomerMapper.customerRegisterRequestToCustomer(customerRegisterRequest);
        customer.setPassword(passwordEncoder.encode(customerRegisterRequest.getPassword()));
        customer.setRole(Role.CUSTOMER);
        customer.setActive(true);
        customer.setDeleted(false);

        final TokenResponse tokenResponse = this.authService.generateToken(customer);

        final CustomerRegisterResponse response = CustomerMapper.toCustomerRegisterResponse(
                customer,
                tokenResponse
        );
        this.customerRepository.save(customer);
        return response;
    }


}
