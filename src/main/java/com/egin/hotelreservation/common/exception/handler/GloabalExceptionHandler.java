package com.egin.hotelreservation.common.exception.handler;

import com.egin.hotelreservation.common.exception.AppException;
import com.egin.hotelreservation.common.exception.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.AuthenticationException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GloabalExceptionHandler {

    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse<Object> handleAppException(AppException appException) {

        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusMessage(HttpStatus.BAD_REQUEST.name())
                .isSuccess(false)
                .message(appException.getMessage())
                .build();
    }

//    @ExceptionHandler(HttpClientErrorException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ExceptionResponse<Object> handleAuthenticationException(Exception exception) {
//
//        return ExceptionResponse.builder()
//                .httpStatus(HttpStatus.FORBIDDEN)
//                .statusMessage(HttpStatus.FORBIDDEN.name())
//                .isSuccess(false)
//                .message(exception.getMessage())
//                .build();
//    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationExceptions = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(ex -> validationExceptions.put(ex.getField(), ex.getDefaultMessage()));

        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusMessage(HttpStatus.BAD_REQUEST.name())
                .isSuccess(false)
                .message(validationExceptions)
                .build();

    }

//    @ExceptionHandler(SignatureException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ExceptionResponse<Object> handleJwtSignatureException(SignatureException exception) {
//
//        return ExceptionResponse.builder()
//                .httpStatus(HttpStatus.FORBIDDEN)
//                .statusMessage(HttpStatus.FORBIDDEN.name())
//                .isSuccess(false)
//                .message(exception.getMessage())
//                .build();
//    }

}
