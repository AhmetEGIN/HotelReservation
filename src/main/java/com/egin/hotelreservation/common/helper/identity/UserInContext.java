package com.egin.hotelreservation.common.helper.identity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInContext {

    public String getUsername() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
        return name;
    }

}
