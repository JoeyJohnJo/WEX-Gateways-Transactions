package com.wex.gateways.transactions.app.domain.dtos;

import java.math.BigDecimal;

public record ExchangeRateDto(String currency, BigDecimal exchangeRate) {
}
