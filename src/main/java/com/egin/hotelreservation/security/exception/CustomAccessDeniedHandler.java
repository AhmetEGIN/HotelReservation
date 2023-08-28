package com.egin.hotelreservation.security.exception;

import com.egin.hotelreservation.common.exception.model.ExceptionResponse;
import com.egin.hotelreservation.security.constants.ExceptionMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null){
//            new ObjectMapper().writeValue(response.getOutputStream(), "Kullanıcı yetkiye sahip değil");

            final ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .statusMessage(HttpStatus.FORBIDDEN.name())
                    .isSuccess(false)
                    .message(ExceptionMessages.FORBIDDEN)
                    .build();

            final String responseBody = new ObjectMapper()
                    .writeValueAsString(exceptionResponse);

            response.getOutputStream().write(responseBody.getBytes());
            return;
        }

//        final ExceptionResponse exceptionResponse = ExceptionResponse.builder()
//                .httpStatus(HttpStatus.FORBIDDEN)
//                .statusMessage(HttpStatus.FORBIDDEN.name())
//                .isSuccess(false)
//                .message(ExceptionMessages.FORBIDDEN)
//                .build();
//
//        final String responseBody = new ObjectMapper()
//                .writeValueAsString(exceptionResponse);
//
//        response.getOutputStream().write(responseBody.getBytes());

    }
}
