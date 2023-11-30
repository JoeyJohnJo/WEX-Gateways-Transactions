package com.wex.gateways.transactions.app.httpclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiscalDataRatesOfExchangeResponseDto {
    private List<FiscalDataRateOfExchangeEntryDto> data;

    public List<FiscalDataRateOfExchangeEntryDto> getData() {
        return data;
    }

    public void setData(List<FiscalDataRateOfExchangeEntryDto> data) {
        this.data = data;
    }
}
