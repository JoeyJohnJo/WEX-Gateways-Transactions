package com.wex.gateways.transactions.app.domain.database.repositories;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, UUID> {
}
