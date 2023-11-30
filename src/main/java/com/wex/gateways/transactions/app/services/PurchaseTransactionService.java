package com.wex.gateways.transactions.app.services;

import com.wex.gateways.transactions.app.database.entities.PurchaseTransaction;
import jakarta.validation.Valid;

public interface PurchaseTransactionService {

    public PurchaseTransaction storePurchaseTransaction(@Valid PurchaseTransaction purchaseTransaction);
}
