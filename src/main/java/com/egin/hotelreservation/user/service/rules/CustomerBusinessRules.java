package com.egin.hotelreservation.user.service.rules;

import com.egin.hotelreservation.user.exception.EmailAlreadyExistException;
import com.egin.hotelreservation.user.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerBusinessRules {

    private final CustomerRepository customerRepository;

    public CustomerBusinessRules(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    /**
     * Verilen email değerinin başka bir kullanıcı tarafından kullanılıp kullanılmadığına dair
     * bilgi veren metottur.
     * @param email
     */
    public void checkEmailExist(String email) {

        if (this.customerRepository.existsCustomerByEmailEquals(email)) {
            throw new EmailAlreadyExistException();
        }

    }


}
