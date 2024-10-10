package com.starter.core.config.exception;


public class ExpiredTokenException extends TokenException {

    public ExpiredTokenException() {
        super(ExceptionType.EXPIRED_TOKEN);
    }

}
