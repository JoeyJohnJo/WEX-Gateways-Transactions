package com.wex.gateways.transactions.services;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import com.wex.gateways.transactions.app.domain.database.repositories.PurchaseTransactionRepository;
import com.wex.gateways.transactions.app.domain.services.PurchaseTransactionService;
import com.wex.gateways.transactions.app.domain.services.PurchaseTransactionServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PurchaseTransactionServiceTest {

    @Test
    void testStorePurchaseTransaction() {

        PurchaseTransactionRepository purchaseTransactionRepository = mock(PurchaseTransactionRepository.class);
        PurchaseTransactionService purchaseTransactionService = new PurchaseTransactionServiceImpl(purchaseTransactionRepository);

        String description = "Test Purchase";
        Timestamp transactionDate = Timestamp.from(Instant.now());
        int purchaseAmount = 2550;

        when(purchaseTransactionRepository.save(any(PurchaseTransaction.class)))
            .thenReturn(new PurchaseTransaction(UUID.randomUUID(), description, transactionDate, purchaseAmount));

        PurchaseTransaction storedTransaction = purchaseTransactionService.storePurchaseTransaction(new PurchaseTransaction(description, transactionDate, purchaseAmount));

        assertNotNull(storedTransaction.getId());
        assertEquals(description, storedTransaction.getDescription());
        assertEquals(transactionDate, storedTransaction.getDate());
        assertEquals(purchaseAmount, storedTransaction.getAmount());

        verify(purchaseTransactionRepository).save(any(PurchaseTransaction.class));
    }
}
