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

    public static final String SUCCESS_CODE = "20000";
    public static final String SUCCESS_MESSAGE = "API 호출 성공";

    private String resultCode;
    private String resultMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private D resultData;

    public static ApiResponse<?> success() {
        return ApiResponse.builder()
            .resultCode(SUCCESS_CODE)
            .resultMessage(SUCCESS_MESSAGE)
            .build();
    }

    public static <D> ApiResponse<D> success(D responseData) {
        return ApiResponse.<D>builder()
            .resultCode(SUCCESS_CODE)
            .resultMessage(SUCCESS_MESSAGE)
            .resultData(responseData)
            .build();
    }

    public static ApiResponse<?> exception(ExceptionType responseType) {
        return ApiResponse.exception(responseType, null);
    }

    public static ApiResponse<?> exception(ExceptionType responseType, String responseMessage) {
        return ApiResponse.builder()
            .resultCode(responseType.getCode())
            .resultMessage(Optional.ofNullable(responseMessage).orElse(responseType.getMessage()))
            .build();
    }

}
