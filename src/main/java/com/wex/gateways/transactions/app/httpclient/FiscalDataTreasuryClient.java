package com.wex.gateways.transactions.app.httpclient;

import com.wex.gateways.transactions.app.domain.dtos.ExchangeRateDto;
import com.wex.gateways.transactions.app.domain.exceptions.errors.ExchangeRateNotAvailableException;
import com.wex.gateways.transactions.app.domain.services.FetchExchangeRate;
import com.wex.gateways.transactions.app.httpclient.dtos.FiscalDataRatesOfExchangeResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FiscalDataTreasuryClient implements FetchExchangeRate {

    @Value("${api.fiscaldata.base}")
    private String baseUrl;

    private static final String ratesOfExchangeEndpoint = "/v1/accounting/od/rates_of_exchange";
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public ExchangeRateDto fetchExchangeRate(String currency, Timestamp date) {
        var response = RestClient.create(baseUrl).get()
            .uri(uriBuilder -> uriBuilder
                .path(ratesOfExchangeEndpoint)
                .queryParam("fields", "country_currency_desc,exchange_rate,record_date")
                .queryParam("filter", String.format("country_currency_desc:in:(%s),record_date:lte:%s", currency, formatTimestamp(date)))
                .queryParam("sort", "-record_date")
                .queryParam("page[size]", "1")
                .build())
            .retrieve();

        return Optional.ofNullable(response.toEntity(FiscalDataRatesOfExchangeResponseDto.class).getBody())
            .map(FiscalDataRatesOfExchangeResponseDto::getData)
            .map(List::getFirst)
            .map(it -> new ExchangeRateDto(
                it.getCountryCurrencyDesc(),
                new BigDecimal(it.getExchangeRate()),
                convertToTimestamp(it.getRecordDate())
            ))
            .orElseThrow(ExchangeRateNotAvailableException::new);
    }

    private String formatTimestamp(Timestamp timestamp) {
        return new SimpleDateFormat(DATE_PATTERN).format(timestamp);
    }

    public static Timestamp convertToTimestamp(String dateString) {
        try {
            return new Timestamp(new SimpleDateFormat(DATE_PATTERN).parse(dateString).getTime());
        } catch (ParseException e) {
            throw new ExchangeRateNotAvailableException();
        }
    }

}
