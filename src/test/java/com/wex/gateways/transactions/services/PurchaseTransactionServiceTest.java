package com.wex.gateways.transactions.services;

import com.wex.gateways.transactions.app.database.entities.PurchaseTransaction;
import com.wex.gateways.transactions.app.database.repositories.PurchaseTransactionRepository;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PurchaseTransactionServiceTest {

    @Test
    void testStorePurchaseTransaction() {
        // Arrange
        PurchaseTransactionRepository purchaseTransactionRepositoryClass = mock(PurchaseTransactionRepository.class);
        PurchaseTransactionService purchaseTransactionService = new PurchaseTransactionService(purchaseTransactionRepositoryClass);

        String description = "Test Purchase";
        Timestamp transactionDate = Timestamp.from(Instant.now());
        int purchaseAmount = 2550;

        PurchaseTransaction storedTransaction = purchaseTransactionService.storePurchaseTransaction(description, transactionDate, purchaseAmount);

        assertNotNull(storedTransaction.getId());
        assertEquals(description, storedTransaction.getDescription());
        assertEquals(transactionDate, storedTransaction.getDate());
        assertEquals(purchaseAmount, storedTransaction.getAmount());

        verify(purchaseTransactionRepositoryClass).save(any(PurchaseTransaction.class));
    }
}
