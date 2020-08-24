package com.procarihana.accounting.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
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
        return ResponseEntity.status(exception.getStatusCode() != 0
            ? exception.getStatusCode()
            : HttpStatus.INTERNAL_SERVER_ERROR.value()) //如果为空就返回500
            .body(errorResponse);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    ResponseEntity<?> handleIncorrectCredentialsException(IncorrectCredentialsException exception) {
        final Integer clineErrorStatus = 400;
        ErrorResponse errorResponse = ErrorResponse.builder()
            .statusCode(clineErrorStatus)  //传进来就是cline 400
            .code("INCORRECT_CREDENTIALS")
            .errorType(ServiceException.ErrorType.Cline) //密码输入错误为Cline的问题
            .massage(exception.getMessage())
            .build();
        return ResponseEntity.status(clineErrorStatus)
            .body(errorResponse);
    }
}
