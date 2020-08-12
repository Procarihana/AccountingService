package com.Procarihana.AccountingService.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException{
    public InvalidParameterException(String message){
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value()); //BAD_REQUEST.value()为400w
        this.setErrorCode("INVALID_PARAMETER");
        this.setErrorType(ErrorType.Cline);
    }
}
