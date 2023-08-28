package com.egin.hotelreservation.common.exception.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionResponse<T> {

//    @Builder.Default
//    private LocalDateTime createdDate = LocalDateTime.now();
    
    private HttpStatus httpStatus;

    private String statusMessage;

    private T message;

    private boolean isSuccess;

}
