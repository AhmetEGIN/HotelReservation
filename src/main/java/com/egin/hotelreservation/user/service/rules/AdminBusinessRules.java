package com.egin.hotelreservation.user.service.rules;

import com.egin.hotelreservation.user.exception.EmailAlreadyExistException;
import com.egin.hotelreservation.user.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminBusinessRules {

    private final AdminRepository adminRepository;

    public AdminBusinessRules(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    /**
     * Verilen email değerinin başka bir kullanıcı tarafından kullanılıp kullanılmadığına dair
     * bilgi veren metottur.
     * @param email
     */
    public void checkEmailExist(String email) {
        if (this.adminRepository.existsAdminByEmailEquals(email)) {
            throw new EmailAlreadyExistException();
        }
    }
}
