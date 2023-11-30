package com.wex.gateways.transactions.app.domain.services;

import com.wex.gateways.transactions.app.domain.dtos.ExchangeRateDto;

import java.sql.Timestamp;

/**
 * Interface for fetching exchange rates based on currency and date.
 */
public interface FetchExchangeRate {

    /**
     * Fetches the exchange rate for a given currency and date.
     *
     * @param currency The currency for which the exchange rate is requested.
     * @param date     The date for which the exchange rate is requested.
     * @return ExchangeRateDto containing the currency, exchange rate, and date.
     */
    ExchangeRateDto fetchExchangeRate(String currency, Timestamp date);
}
