package com.wex.gateways.transactions.app.httpclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Represents a response DTO from the fiscal data treasury api.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiscalDataRatesOfExchangeResponseDto {
    private List<FiscalDataRateOfExchangeEntryDto> data;

    /**
     * Gets the list of fiscal data rate of exchange entries.
     *
     * @return The list of fiscal data rate of exchange entries.
     */
    public List<FiscalDataRateOfExchangeEntryDto> getData() {
        return data;
    }

    /**
     * Sets the list of fiscal data rate of exchange entries.
     *
     * @param data The list of fiscal data rate of exchange entries to set.
     */
    public void setData(List<FiscalDataRateOfExchangeEntryDto> data) {
        this.data = data;
    }
}
