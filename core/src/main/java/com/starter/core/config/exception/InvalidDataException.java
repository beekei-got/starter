package com.starter.core.config.exception;

public class InvalidDataException extends ApiException {

    public InvalidDataException(ExceptionMessageType exceptionMessageType) {
        super(ExceptionType.INVALID_DATA, exceptionMessageType.getMessage());
    }

}
