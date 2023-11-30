package com.wex.gateways.transactions.app.domain.dtos;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.UUID;

public record PurchaseTransactionDetailsDto(UUID id, String description, Timestamp date, BigDecimal usDollarAmount, int usCentsAmount) {
    public static PurchaseTransactionDetailsDto from(PurchaseTransaction purchaseTransaction) {
        return new PurchaseTransactionDetailsDto(
            purchaseTransaction.getId(),
            purchaseTransaction.getDescription(),
            purchaseTransaction.getDate(),
            purchaseTransaction.getDollarAmount(),
            purchaseTransaction.getAmount()
        );
    }
}
