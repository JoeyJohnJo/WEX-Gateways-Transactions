package com.wex.gateways.transactions.app.domain.services;

import com.wex.gateways.transactions.app.domain.dtos.ExchangeRateDto;

import java.sql.Timestamp;

public interface FetchExchangeRate {

    ExchangeRateDto fetchExchangeRate(String currency, Timestamp date);
}
