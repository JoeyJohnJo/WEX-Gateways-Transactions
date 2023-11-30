package com.wex.gateways.transactions.app.domain.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) representing exchange rate information for a specific currency at a certain date.
 * This is used to transfer exchange rate data between different layers of the application.
 */
public record ExchangeRateDto(String currency, BigDecimal exchangeRate, Timestamp date) {
}
