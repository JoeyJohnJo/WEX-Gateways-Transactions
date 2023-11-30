package com.wex.gateways.transactions.app.domain.services;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionCurrencyConvertedDetailsDto;
import jakarta.validation.Valid;

import java.util.UUID;

/**
 * Service interface for handling purchase transactions.
 */
public interface PurchaseTransactionService {

    /**
     * Stores a purchase transaction.
     *
     * @param purchaseTransaction The purchase transaction to be stored.
     * @return The stored purchase transaction.
     */
    PurchaseTransaction storePurchaseTransaction(@Valid PurchaseTransaction purchaseTransaction);

    /**
     * Retrieves a purchase transaction in the specified currency.
     *
     * @param id       The unique identifier of the purchase transaction.
     * @param currency The target currency for conversion.
     * @return PurchaseTransactionCurrencyConvertedDetailsDto containing details of the converted purchase transaction.
     */
    PurchaseTransactionCurrencyConvertedDetailsDto getPurchaseTransactionInCurrency(UUID id, String currency);
}
