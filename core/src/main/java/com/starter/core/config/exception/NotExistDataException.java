package com.starter.core.config.exception;


public class NotExistDataException extends ApiException {

    public NotExistDataException(ExceptionMessageType exceptionMessageType) {
        super(ExceptionType.NOT_EXIST_DATA, exceptionMessageType.getMessage());
    }

}
