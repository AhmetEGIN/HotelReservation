package com.egin.hotelreservation.security.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete")  ,

    CUSTOMER_READ("customer:read"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_CREATE("customer:create"),
    CUSTOMER_DELETE("customer:delete"),

    HOTEL_READ("hotel:read"),
    HOTEL_UPDATE("hotel:update"),
    HOTEL_CREATE("hotel:create"),
    HOTEL_DELETE("hotel:delete")

            ;

    @Getter
    private final String permissions;




}
