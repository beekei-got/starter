package com.starter.core.config.exception;

public class UnsupportedTypeException extends ApiException {

	public UnsupportedTypeException(ExceptionMessageType exceptionMessageType) {
		super(ExceptionType.UNSUPPORTED_TYPE, exceptionMessageType.getMessage());
	}

}
