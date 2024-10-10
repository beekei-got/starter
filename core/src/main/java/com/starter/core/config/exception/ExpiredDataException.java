package com.starter.core.config.exception;


public class ExpiredDataException extends ApiException {

    public ExpiredDataException(ExceptionMessageType exceptionMessageType) {
        super(ExceptionType.EXPIRED_DATA, exceptionMessageType.getMessage());
    }

}
