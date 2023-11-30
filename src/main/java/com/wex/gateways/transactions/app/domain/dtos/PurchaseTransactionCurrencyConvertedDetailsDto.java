package com.wex.gateways.transactions.app.domain.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public record PurchaseTransactionCurrencyConvertedDetailsDto(
    UUID id,
    String description,
    Timestamp date,
    BigDecimal usDollarAmount,
    int usCentsAmount,
    String currency,
    BigDecimal exchangeRate,
    BigDecimal convertedAmount
) {
}
