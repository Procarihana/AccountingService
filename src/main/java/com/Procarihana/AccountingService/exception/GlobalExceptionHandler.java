package com.Procarihana.AccountingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> handleParameterException(ServiceException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(exception.getStatusCode())
                .code("USER_NOT_FOUND")
                .errorType(exception.getErrorType())
                .massage(exception.getMessage())
                .build();
        return ResponseEntity.status(exception.getStatusCode() != 0 ?
                exception.getStatusCode()
                : HttpStatus.INTERNAL_SERVER_ERROR.value()) //如果为空就返回500
                .body(errorResponse);
    }
}
