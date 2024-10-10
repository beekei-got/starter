package com.starter.core.config.exception;

import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {

    private final ExceptionType exceptionType;

    public TokenException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

}
