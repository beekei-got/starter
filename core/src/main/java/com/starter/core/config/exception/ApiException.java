package com.starter.core.config.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final ExceptionType exceptionType;
    private final String message;

    public ApiException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = exceptionType.getMessage();
    }

    public ApiException(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }

}
