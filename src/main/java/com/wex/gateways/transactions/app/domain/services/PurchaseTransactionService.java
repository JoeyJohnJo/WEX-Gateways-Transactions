package com.wex.gateways.transactions.app.domain.services;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import jakarta.validation.Valid;

public interface PurchaseTransactionService {

    public PurchaseTransaction storePurchaseTransaction(@Valid PurchaseTransaction purchaseTransaction);
}
