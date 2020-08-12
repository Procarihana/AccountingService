package com.Procarihana.AccountingService.exception;

import lombok.Data;

/**
 * Accounting Service Exception
 */
@Data
public class ServiceException extends RuntimeException {
    private int statusCode;
    private String errorCode;//biz error code
    private ServiceException.ErrorType errorType;//Service,Client,Unknown

    public enum ErrorType {
        Cline,
        Service,
        Unknown
    }

    public ServiceException(String message) {
        super(message);
    }
}
