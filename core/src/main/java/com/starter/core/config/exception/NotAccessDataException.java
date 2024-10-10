package com.starter.core.config.exception;


public class NotAccessDataException extends ApiException {

    public NotAccessDataException(ExceptionMessageType exceptionMessageType) {
        super(ExceptionType.NOT_ACCESS_DATA, exceptionMessageType.getMessage());
    }

}
