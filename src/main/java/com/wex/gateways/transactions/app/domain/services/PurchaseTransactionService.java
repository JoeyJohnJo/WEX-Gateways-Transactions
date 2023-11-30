package com.wex.gateways.transactions.app.domain.services;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionCurrencyConvertedDetailsDto;
import jakarta.validation.Valid;

import java.util.UUID;

public interface PurchaseTransactionService {

    PurchaseTransaction storePurchaseTransaction(@Valid PurchaseTransaction purchaseTransaction);

    PurchaseTransactionCurrencyConvertedDetailsDto getPurchaseTransactionInCurrency(UUID id, String currency);
}
