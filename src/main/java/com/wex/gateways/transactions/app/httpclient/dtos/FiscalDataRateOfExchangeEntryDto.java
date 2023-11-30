package com.wex.gateways.transactions.app.httpclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents exchange rate data fetched from the fiscal data treasury api.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiscalDataRateOfExchangeEntryDto {

    @JsonProperty("exchange_rate")
    private String exchangeRate;

    @JsonProperty("country_currency_desc")
    private String countryCurrencyDesc;

    @JsonProperty("record_date")
    private String recordDate;

    /**
     * Gets the exchange rate.
     *
     * @return The exchange rate.
     */
    public String getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Sets the exchange rate.
     *
     * @param exchangeRate The exchange rate to set.
     */
    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * Gets the country currency description.
     *
     * @return The country currency description.
     */
    public String getCountryCurrencyDesc() {
        return countryCurrencyDesc;
    }

    /**
     * Sets the country currency description.
     *
     * @param countryCurrencyDesc The country currency description to set.
     */
    public void setCountryCurrencyDesc(String countryCurrencyDesc) {
        this.countryCurrencyDesc = countryCurrencyDesc;
    }

    /**
     * Gets the record date.
     *
     * @return The record date.
     */
    public String getRecordDate() {
        return recordDate;
    }

    /**
     * Sets the record date.
     *
     * @param recordDate The record date to set.
     */
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
