package com.wex.gateways.transactions.app.domain.database.repositories;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for managing {@link PurchaseTransaction} entities in the database.
 * Extends the JpaRepository interface, providing CRUD operations and additional query methods.
 */
public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, UUID> {
    // No additional methods are declared here since JpaRepository provides CRUD operations.
    // Custom query methods can be added if needed for specific querying requirements.
}
