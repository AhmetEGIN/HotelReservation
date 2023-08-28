package com.egin.hotelreservation.security.exception;

import com.egin.hotelreservation.common.exception.model.ExceptionResponse;
import com.egin.hotelreservation.security.constants.ExceptionMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.security.SignatureException;

@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null){
//            new ObjectMapper().writeValue(response.getOutputStream(), "Kullanıcı yetkiye sahip değil");
            final ExceptionResponse<Object> exceptionResponse = ExceptionResponse.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .statusMessage(HttpStatus.UNAUTHORIZED.name())
                    .isSuccess(false)
                    .message(ExceptionMessages.UNAUTHORIZED)
                    .build();

            final String responseBody = new ObjectMapper()
                    .writeValueAsString(exceptionResponse);

            response.getOutputStream().write(responseBody.getBytes());
            return;
        }

//        final ExceptionResponse<Object> exceptionResponse = ExceptionResponse.builder()
//                .httpStatus(HttpStatus.UNAUTHORIZED)
//                .statusMessage(HttpStatus.UNAUTHORIZED.name())
//                .isSuccess(false)
//                .message(ExceptionMessages.UNAUTHORIZED)
//                .build();
//
//        final String responseBody = new ObjectMapper()
//                .writeValueAsString(exceptionResponse);
//
//        response.getOutputStream().write(responseBody.getBytes());


    }
}
