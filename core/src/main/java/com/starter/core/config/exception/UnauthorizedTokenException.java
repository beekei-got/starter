package com.starter.core.config.exception;


public class UnauthorizedTokenException extends TokenException {

    public UnauthorizedTokenException() {
        super(ExceptionType.UNAUTHORIZED_TOKEN);
    }

}
