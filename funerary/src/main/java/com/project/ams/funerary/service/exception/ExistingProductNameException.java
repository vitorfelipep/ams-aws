package com.project.ams.funerary.service.exception;

import org.springframework.http.HttpStatus;

public class ExistingProductNameException extends NegocioException {

    private static final long serialVersionUID = -8235309123594131326L;

    public ExistingProductNameException() {
        super("business-error-3", HttpStatus.BAD_REQUEST);
    }
}