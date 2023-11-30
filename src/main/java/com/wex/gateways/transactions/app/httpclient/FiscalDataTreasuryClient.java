package com.wex.gateways.transactions.app.httpclient;

import com.wex.gateways.transactions.app.domain.dtos.ExchangeRateDto;
import com.wex.gateways.transactions.app.domain.services.FetchExchangeRate;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.sql.Timestamp;

@Service
public class FiscalDataTreasuryClient implements FetchExchangeRate {

    @Value("${api.fiscaldata.base}")
    private String baseUrl;

    private static final String ratesOfExchangeEndpoint = "/v1/accounting/od/rates_of_exchange";

    @Override
    public ExchangeRateDto fetchExchangeRate(String currency, Timestamp date) {
        var restClient = RestClient.builder()
            .requestFactory(new HttpComponentsClientHttpRequestFactory())
            .baseUrl(baseUrl)
            .build();

        restClient.get().uri(ratesOfExchangeEndpoint).retrieve().toEntity(String.class);
        return null;
    }
}
