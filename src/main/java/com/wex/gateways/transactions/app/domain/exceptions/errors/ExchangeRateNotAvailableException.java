package com.wex.gateways.transactions.app.domain.exceptions.errors;

/**
 * Exception thrown when the exchange rate for a specific currency and date is not available.
 * This exception is typically thrown when attempting to retrieve an exchange rate that is not present in the system.
 */
public class ExchangeRateNotAvailableException extends LocalizedException {

    // Base name for resource bundle messages related to this exception
    private static final String NOT_FOUND_EXCEPTION_BASENAME = "exception.exchangeRateNotAvailable";

    /**
     * Constructs a new ExchangeRateNotAvailableException with a default message.
     */
    public ExchangeRateNotAvailableException() {
        super(NOT_FOUND_EXCEPTION_BASENAME);
    }
}
