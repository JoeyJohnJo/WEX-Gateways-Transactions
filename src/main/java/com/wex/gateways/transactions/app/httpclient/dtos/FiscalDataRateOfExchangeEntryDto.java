package com.wex.gateways.transactions.app.httpclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiscalDataRateOfExchangeEntryDto {

    @JsonProperty("exchange_rate")
    private String exchangeRate;

    @JsonProperty("country_currency_desc")
    private String countryCurrencyDesc;

    @JsonProperty("record_date")
    private String recordDate;

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getCountryCurrencyDesc() {
        return countryCurrencyDesc;
    }

    public void setCountryCurrencyDesc(String countryCurrencyDesc) {
        this.countryCurrencyDesc = countryCurrencyDesc;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
