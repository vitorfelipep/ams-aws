package com.project.ams.funerary.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author vitor
 *
 */

@RequiredArgsConstructor
@Getter
public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = -619873074592148520L;

    private final String code;
    private final HttpStatus status;
}
