package com.egin.hotelreservation.auth.service;


import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.security.model.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Authentication işlemleri için kullanılacak olan Service' i inject etmek için
 * kullanılacak olan interface'dir.
 *
 */
public interface AuthService {

    TokenResponse generateToken(User user);

    TokenResponse authenticate(LoginRequest authenticationRequest);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;


}
