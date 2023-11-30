package com.wex.gateways.transactions.app.domain.exceptions.errors;

/**
 * Exception thrown when an invalid value is encountered in the application.
 * This exception is typically thrown when validating input values or enforcing constraints.
 */
public class InvalidValueException extends LocalizedException {

    // Base name for resource bundle messages related to this exception
    private static final String INVALID_VALUE_EXCEPTION_BASENAME = "exception.invalidValue";

    // The value that is considered faulty or invalid
    private final Object faultyValue;

    /**
     * Constructs a new InvalidValueException with a default message and the provided faulty value.
     *
     * @param faultyValue The value considered faulty or invalid.
     */
    public InvalidValueException(Object faultyValue) {
        super(INVALID_VALUE_EXCEPTION_BASENAME);
        this.faultyValue = faultyValue;
    }

    /**
     * Gets the value that is considered faulty or invalid.
     *
     * @return The faulty value.
     */
    public Object getFaultyValue() {
        return faultyValue;
    }
}
