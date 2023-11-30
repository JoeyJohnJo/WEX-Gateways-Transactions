package com.wex.gateways.transactions.app.domain.services;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import com.wex.gateways.transactions.app.domain.database.repositories.PurchaseTransactionRepository;
import com.wex.gateways.transactions.app.domain.exceptions.errors.InvalidValueException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

import static com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction.MAX_DESCRIPTION_LENGTH;

@Service
public class PurchaseTransactionServiceImpl implements PurchaseTransactionService {

    private final PurchaseTransactionRepository purchaseTransactionRepository;

    public PurchaseTransactionServiceImpl(PurchaseTransactionRepository purchaseTransactionRepository) {
        this.purchaseTransactionRepository = purchaseTransactionRepository;
    }

    @Override
    public PurchaseTransaction storePurchaseTransaction(@Valid PurchaseTransaction purchaseTransaction) {
        assertPurchaseTransactionIsValid(purchaseTransaction);
        return purchaseTransactionRepository.save(purchaseTransaction);
    }

    private void assertPurchaseTransactionIsValid(PurchaseTransaction purchaseTransaction) {
        if (purchaseTransaction.getId() != null)
            throw new InvalidValueException(purchaseTransaction.getId());

        if (purchaseTransaction.getDescription().trim().length() > MAX_DESCRIPTION_LENGTH)
            throw new InvalidValueException(purchaseTransaction.getDescription());

        if (purchaseTransaction.getDate() == null || purchaseTransaction.getDate().after(Timestamp.from(Instant.now())))
            throw new InvalidValueException(purchaseTransaction.getDate());

        if (purchaseTransaction.getAmount() <= 0)
            throw new InvalidValueException(purchaseTransaction.getAmount());
    }
}
