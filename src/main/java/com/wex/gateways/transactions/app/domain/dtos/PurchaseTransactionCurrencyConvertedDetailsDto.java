package com.wex.gateways.transactions.app.domain.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing purchase transaction details with currency conversion information.
 * This DTO is used to transfer transaction details along with the corresponding currency conversion details.
 */
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
