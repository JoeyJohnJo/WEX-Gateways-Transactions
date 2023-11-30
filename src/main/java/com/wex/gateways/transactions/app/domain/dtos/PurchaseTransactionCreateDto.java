package com.wex.gateways.transactions.app.domain.dtos;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

import static com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction.MAX_DESCRIPTION_LENGTH;

public class PurchaseTransactionCreateDto {
    @NotBlank
    @Size(min = 1, max = MAX_DESCRIPTION_LENGTH)
    private String description;
    @NotNull
    private Timestamp date;

    @NotNull
    @Min(value = 1)
    private int amount;

    public PurchaseTransactionCreateDto() {
    }

    public PurchaseTransactionCreateDto(String description, Timestamp date, int amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PurchaseTransaction toPurchaseTransaction() {
        return new PurchaseTransaction(description, date, amount);
    }
}
