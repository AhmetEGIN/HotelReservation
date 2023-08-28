package com.egin.hotelreservation.common.model.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class BaseResponse<T> {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    private HttpStatus httpStatus;

    private boolean isSuccess;

    private T response;

    public static BaseResponse<Void> SUCCESS = BaseResponse
            .<Void>builder()
            .httpStatus(HttpStatus.OK)
            .isSuccess(true)
            .build();

    public static BaseResponse<Void> NO_CONTENT = BaseResponse
            .<Void>builder()
            .httpStatus(HttpStatus.NO_CONTENT)
            .isSuccess(true)
            .build();

    public static <T> BaseResponse<T> successOf(T response) {
        return BaseResponse
                .<T>builder()
                .httpStatus(HttpStatus.OK)
                .response(response)
                .isSuccess(true)
                .build();

    }

    public static <T> BaseResponse<T> createdOf(T response) {
        return BaseResponse
                .<T>builder()
                .httpStatus(HttpStatus.CREATED)
                .response(response)
                .isSuccess(true)
                .build();

    }







}
