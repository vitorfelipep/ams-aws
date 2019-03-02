package com.project.ams.funerary.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Order
@RestControllerAdvice
public class GeneralExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ApiExcpetionHandler.class);

    @Autowired
    private ApiExcpetionHandler apiExcpetionHandler;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(Exception exception
            , Locale locale) {
        LOG.error("Error not expected", exception);
        final String errorCode = "errorServer-1";
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.of(status, apiExcpetionHandler.toApiError(errorCode, locale));
        return ResponseEntity.status(status).body(errorResponse);
    }
}
