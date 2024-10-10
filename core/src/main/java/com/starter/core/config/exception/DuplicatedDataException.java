package com.starter.core.config.exception;


public class DuplicatedDataException extends ApiException {

    public DuplicatedDataException(ExceptionMessageType exceptionMessageType) {
        super(ExceptionType.DUPLICATED_DATA, exceptionMessageType.getMessage());
    }

}
