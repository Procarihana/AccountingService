package com.procarihana.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Accounting Service ResourceNotFoundException.
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ServiceException {
    /**
     * Constructor for ResourceNotFoundException.
     * @param message throw message.
     */

    public ResourceNotFoundException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
        this.setErrorCode("USER_INFO_NOT_FOUND");
        this.setErrorType(ErrorType.Cline);
    }
}
