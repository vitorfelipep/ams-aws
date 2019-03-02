package com.project.ams.funerary.service.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends NegocioException {

    private static final long serialVersionUID = -8235309123594131326L;

    public ProductNotFoundException() {
        super("business-error-5", HttpStatus.BAD_REQUEST);
    }
}
