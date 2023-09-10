package com.egin.hotelreservation.security.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.egin.hotelreservation.security.model.user.Permission.*;

@RequiredArgsConstructor
public enum Role implements Serializable {

    USER(Collections.emptySet()),

    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    CUSTOMER_READ,
                    CUSTOMER_CREATE,
                    CUSTOMER_UPDATE,
                    CUSTOMER_DELETE
            )
    ),

    CUSTOMER(
            Set.of(
                    CUSTOMER_READ,
                    CUSTOMER_CREATE,
                    CUSTOMER_UPDATE,
                    CUSTOMER_DELETE
            )
    ),
    HOTEL(
            Set.of(
                    HOTEL_READ,
                    HOTEL_CREATE,
                    HOTEL_UPDATE,
                    HOTEL_DELETE
            )
    )
    ;

    @Getter
    private final Set<Permission> permissions;


    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
