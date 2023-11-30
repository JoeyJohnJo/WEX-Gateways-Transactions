package com.wex.gateways.transactions.app.domain.exceptions;

import com.wex.gateways.transactions.app.domain.exceptions.errors.InvalidValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResolver {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorResponseBody> invalidValueException(InvalidValueException exception) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseBody(exception.getFaultyValue(), exception.getLocalizedMessage()));
    }
}
