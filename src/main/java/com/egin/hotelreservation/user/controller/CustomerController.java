package com.egin.hotelreservation.user.controller;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.common.model.response.BaseResponse;
import com.egin.hotelreservation.user.dto.mappers.CustomerMapper;
import com.egin.hotelreservation.user.dto.requests.customerRequests.CustomerRegisterRequest;
import com.egin.hotelreservation.user.dto.requests.customerRequests.UpdateCustomerRequest;
import com.egin.hotelreservation.user.dto.responses.customerResponses.CustomerRegisterResponse;
import com.egin.hotelreservation.user.dto.responses.customerResponses.GetAllCustomersResponse;
import com.egin.hotelreservation.user.dto.responses.customerResponses.GetCustomerByIdResponse;
import com.egin.hotelreservation.user.dto.responses.customerResponses.UpdateCustomerResponse;
import com.egin.hotelreservation.user.model.Customer;
import com.egin.hotelreservation.user.service.CustomerSaveService;
import com.egin.hotelreservation.user.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Customer Entity nesnesini dışarıya açtığımız Rest Controller class'ıdır.
 */
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerSaveService customerSaveService;
    private final CustomerService customerService;

    public CustomerController(
            CustomerSaveService customerSaveService,
            CustomerService customerService
    ) {
        this.customerSaveService = customerSaveService;
        this.customerService = customerService;
    }


    @PostMapping
    public BaseResponse<CustomerRegisterResponse> registerCustomer(@RequestBody @Valid CustomerRegisterRequest customerRegisterRequest) {

        return BaseResponse.createdOf(this.customerSaveService.add(customerRegisterRequest));
    }


    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@RequestBody LoginRequest loginRequest) {

        return BaseResponse.successOf(this.customerService.loginCustomer(loginRequest));
    }


    @PutMapping
    @PreAuthorize("hasAnyAuthority('customer:update', 'admin:update')")
    public BaseResponse<UpdateCustomerResponse> update(
            @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = this.customerService.update(updateCustomerRequest);

        return BaseResponse.successOf(CustomerMapper.customerToUpdateCustomerResponse(customer));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public BaseResponse delete(@PathVariable("id") String id) {

        this.customerService.delete(id);
        return BaseResponse.NO_CONTENT;
    }

    @DeleteMapping("/soft")
    @PreAuthorize("hasAnyAuthority('customer:delete', 'admin:delete')")
    public BaseResponse softDelete(@RequestParam(name = "isDeleted", defaultValue = "false") boolean isDeleted) {

        this.customerService.softDelete(isDeleted);
        return BaseResponse.NO_CONTENT;
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'customer:read')")
    public BaseResponse<List<GetAllCustomersResponse>> getAll() {
        List<GetAllCustomersResponse> response = this.customerService
                .getAllCustomers()
                .stream()
                .map(CustomerMapper::customerToGetAllCustomersResponse)
                .collect(Collectors.toList());

        return BaseResponse.successOf(response);

    }


    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('customer:read', 'admin:read', 'hotel:read')")
    public BaseResponse<GetCustomerByIdResponse> getCustomerById(@PathVariable("id") String id) {

        Customer customer = this.customerService.getCustomerById(id);
        System.out.println("Controller");
        System.out.println(customer.getAuthorities());
        customer.getAuthorities().stream().forEach(System.out::println);

        return BaseResponse.successOf(CustomerMapper.customerToGetCustomerByIdResponse(customer));
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('customer:update', 'admin:update')")
    public BaseResponse changeActiveState(@RequestParam(name = "status", defaultValue = "true") boolean status) {

        this.customerService.changeActiveState(status);
        return BaseResponse.SUCCESS;
    }

}
