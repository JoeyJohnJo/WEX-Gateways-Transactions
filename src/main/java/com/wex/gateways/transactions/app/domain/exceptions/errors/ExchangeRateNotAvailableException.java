package com.wex.gateways.transactions.app.domain.exceptions.errors;

public class ExchangeRateNotAvailableException extends LocalizedException {

    private static final String NOT_FOUND_EXCEPTION_BASENAME = "exception.exchangeRateNotAvailable";

    public ExchangeRateNotAvailableException() {
        super(NOT_FOUND_EXCEPTION_BASENAME);
    }
}
