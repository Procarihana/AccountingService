package com.procarihana.accounting.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException {
    /**
     * Constructor for  InvalidParameterException.
     * @param message  throw message.
     */
    public InvalidParameterException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value()); //BAD_REQUEST.value()为400w
        this.setErrorCode("INVALID_PARAMETER");
        this.setErrorType(ErrorType.Cline);
    }
}
