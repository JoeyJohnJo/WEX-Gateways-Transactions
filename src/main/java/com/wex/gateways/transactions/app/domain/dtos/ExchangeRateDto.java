package com.wex.gateways.transactions.app.domain.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record ExchangeRateDto(String currency, BigDecimal exchangeRate, Timestamp date) {
}
