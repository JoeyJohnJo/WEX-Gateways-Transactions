package com.wex.gateways.transactions.app.domain.dtos;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing simplified purchase transaction details.
 * This DTO is used to transfer basic details of a purchase transaction without additional information.
 */
public record PurchaseTransactionDetailsDto(UUID id, String description, Timestamp date, BigDecimal usDollarAmount, int usCentsAmount) {

    /**
     * Create a PurchaseTransactionDetailsDto instance from a PurchaseTransaction entity.
     *
     * @param purchaseTransaction The PurchaseTransaction entity.
     * @return The PurchaseTransactionDetailsDto instance.
     */
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
