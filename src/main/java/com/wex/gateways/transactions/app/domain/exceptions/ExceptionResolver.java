package com.wex.gateways.transactions.app.domain.exceptions;

import com.wex.gateways.transactions.app.config.i18n.Localization;
import com.wex.gateways.transactions.app.domain.exceptions.errors.InvalidValueException;
import com.wex.gateways.transactions.app.domain.exceptions.errors.NotFoundException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Optional;

@RestControllerAdvice
public class ExceptionResolver {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseBody> notFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponseBody(exception.getRequestedResource(), exception.getLocalizedMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorResponseBody> invalidValueException(InvalidValueException exception) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponseBody(exception.getFaultyValue(), exception.getLocalizedMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseBody(
            Optional.ofNullable(exception.getFieldError()).map(FieldError::getField).orElse(""),
            Localization.getExceptionMessageForLocale("exception.invalidValue",
                LocaleContextHolder.getLocale())
        ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseBody> httpMessageNotReadableException(HttpMessageNotReadableException unused) {
        return ResponseEntity.badRequest().body(new ErrorResponseBody(null,
            Localization.getExceptionMessageForLocale("exception.httpMessageNotReadableException",
                LocaleContextHolder.getLocale())
        ));
    }
}
