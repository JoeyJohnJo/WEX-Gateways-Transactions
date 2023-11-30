package com.wex.gateways.transactions.app.errors.exceptions;

public class InvalidValueException extends LocalizedException {

    private static final String INVALID_VALUE_EXCEPTION_BASENAME = "exception.invalidValue";
    private final Object faultyValue;

    public InvalidValueException(Object faultyValue) {
        super(INVALID_VALUE_EXCEPTION_BASENAME);
        this.faultyValue = faultyValue;
    }

    public Object getFaultyValue() {
        return faultyValue;
    }
}
