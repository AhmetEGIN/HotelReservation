package com.egin.hotelreservation.user.service.impl;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.auth.service.AuthService;
import com.egin.hotelreservation.common.helper.identity.UserInContext;
import com.egin.hotelreservation.user.dto.requests.customerRequests.UpdateCustomerRequest;
import com.egin.hotelreservation.user.exception.CustomerNotFoundException;
import com.egin.hotelreservation.user.model.Customer;
import com.egin.hotelreservation.user.repository.CustomerRepository;
import com.egin.hotelreservation.user.service.CustomerService;
import com.egin.hotelreservation.user.service.rules.CustomerBusinessRules;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Customer Entity nesnesi üzerinde işlem yapmayı sağlayacak olan Service katmanı class'ıdır.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserInContext userInContext;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final CustomerBusinessRules customerBusinessRules;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            UserInContext userInContext,
            PasswordEncoder passwordEncoder,
            AuthService authService,
            CustomerBusinessRules customerBusinessRules) {
        this.customerRepository = customerRepository;
        this.userInContext = userInContext;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.customerBusinessRules = customerBusinessRules;
    }


    /**
     * Authenticate olmuş kullanıcıya SecurityContext üzerinden ulaşmayı
     * sağlayacak olan metottur.
     * @return
     */
    private Customer getUser() {
        return this.customerRepository
                .findByEmail(this.userInContext.getUsername())
                .orElseThrow(CustomerNotFoundException::new);
    }

    /**
     * Customer entity nesnesin sistemde Login işlemlerini yapmasını sağlayan metottur.
     * @param loginRequest
     * @return
     */
    @Override
    public TokenResponse loginCustomer(final LoginRequest loginRequest) {

        return this.authService.authenticate(loginRequest);
    }

    /**
     * Sistemde kayıtlı olan bir Customer Entity nesnesinin güncellemeye izin verilen bilgilerini
     * güncellemek için kullanılan metottur.
     * @param updateCustomerRequest
     * @return Customer
     */
    @Override
    public Customer update(UpdateCustomerRequest updateCustomerRequest) {

        customerBusinessRules.checkEmailExist(updateCustomerRequest.getEmail());
        final Customer customer = getUser();

        customer.setEmail(updateCustomerRequest.getEmail());
        customer.setPassword(updateCustomerRequest.getPassword());
        customer.setFirstName(updateCustomerRequest.getFirstName());
        customer.setLastName(updateCustomerRequest.getLastName());

        this.customerRepository.save(customer);

        return customer;
    }

    /**
     * Id değeri verilen bir {@link Customer} entity nesnesini silmek için kullanılan metottur.
     * @param id
     */
    @Override
    public void delete(String id) {

        final Customer customer = getCustomerById(id);
        this.customerRepository.delete(customer);

    }

    /**
     * Sistemde Authenticate olmuş bir {@link Customer} entity nesnesi üzerinde soft delete işlemi yapmak için kullanılan metottur.
     * @param isDeleted
     */
    @Override
    public void softDelete(boolean isDeleted) {

        this.getUser().setDeleted(isDeleted);
        this.customerRepository.save(getUser());

    }


    /**
     * Veri tabanındaki tüm {@link Customer} entity'leri getirmek için kullanılan metottur.
     * @return
     */
    @Override
    @Cacheable(value = "customers")
    public List<Customer> getAllCustomers() {

        return this.customerRepository.findAll();
    }


    /**
     * Id değeri verilen {@link Customer} entity nesnesini getirmek için kullanılan metottur.
     * @param id
     * @return
     */
    @Override
    public Customer getCustomerById(String id) {

        return  this.customerRepository
                .findById(id)
                .orElseThrow(CustomerNotFoundException::new);
    }


    /**
     * Authenticate olmuş bir {@link Customer} entity nesnesinin Active parametresini değiştirmek
     * için kullanılan metottur.
     * @param status
     */
    @Override
    public void changeActiveState(boolean status) {
        getUser().setActive(status);
        this.customerRepository.save(getUser());

    }

    @Override
    public Customer getCustomerByEmail(String username) {

        return this.customerRepository.findByEmail(username)
                .orElseThrow(CustomerNotFoundException::new);
    }

}
