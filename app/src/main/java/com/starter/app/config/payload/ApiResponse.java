package com.starter.app.config.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.starter.core.config.exception.ExceptionType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ApiResponse<D> {

    private static final String SUCCESS_CODE = "20000";
    private static final String SUCCESS_MESSAGE = "API 호출 성공";

    private String responseCode;

    private String responseMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private D responseData;

    public static ApiResponse<?> success() {
        return ApiResponse.builder()
            .responseCode(SUCCESS_CODE)
            .responseMessage(SUCCESS_MESSAGE)
            .build();
    }

    public static <D> ApiResponse<D> success(D responseData) {
        return ApiResponse.<D>builder()
            .responseCode(SUCCESS_CODE)
            .responseMessage(SUCCESS_MESSAGE)
            .responseData(responseData)
            .build();
    }

    public static ApiResponse<?> exception(ExceptionType responseType) {
        return ApiResponse.exception(responseType, null);
    }

    public static ApiResponse<?> exception(ExceptionType responseType, String responseMessage) {
        return ApiResponse.builder()
            .responseCode(responseType.getCode())
            .responseMessage(Optional.ofNullable(responseMessage).orElse(responseType.getMessage()))
            .build();
    }

}
