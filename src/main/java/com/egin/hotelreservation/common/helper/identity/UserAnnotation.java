package com.egin.hotelreservation.common.helper.identity;

import com.egin.hotelreservation.security.model.user.User;
import com.egin.hotelreservation.security.repository.UserRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class UserAnnotation {

    private final UserRepository userRepository;
    private final UserInContext userInContext;

    public UserAnnotation(
            UserRepository userRepository,
            UserInContext userInContext) {
        this.userRepository = userRepository;
        this.userInContext = userInContext;
    }

    @Pointcut("@annotation(com.egin.hotelreservation.common.helper.identity.UserIdentity)")
    public void userIdentityAnnotation() {}

    @Before("com.egin.hotelreservation.common.helper.identity.UserAnnotation.userIdentityAnnotation()")
    public User findUserInContext() {
        String email = this.userInContext.getUsername();
        return this.userRepository.findByEmail(email).orElse(null);

    }

}
