package com.Procarihana.AccountingService.exception;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String code;
    private ServiceException.ErrorType errorType;
    private String massage;
    private int statusCode;
}
