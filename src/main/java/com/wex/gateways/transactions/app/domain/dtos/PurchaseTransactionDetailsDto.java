package com.wex.gateways.transactions.app.domain.dtos;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.UUID;

import static com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction.MAX_DESCRIPTION_LENGTH;

public record PurchaseTransactionDetailsDto(UUID id, String description, Timestamp date, int amount) {
    public static PurchaseTransactionDetailsDto from(PurchaseTransaction purchaseTransaction) {
        return new PurchaseTransactionDetailsDto(
            purchaseTransaction.getId(),
            purchaseTransaction.getDescription(),
            purchaseTransaction.getDate(),
            purchaseTransaction.getAmount()
        );
    }
}
