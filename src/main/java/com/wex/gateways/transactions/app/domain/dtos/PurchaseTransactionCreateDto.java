package com.wex.gateways.transactions.app.domain.dtos;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) representing the information required to create a new purchase transaction.
 * Used for receiving input data in the controller layer before converting it to a database entity.
 */
public class PurchaseTransactionCreateDto {

    /**
     * Maximum allowed length for the transaction description.
     */
    public static final int MAX_DESCRIPTION_LENGTH = 50;

    /**
     * Description of the purchase transaction.
     */
    @NotBlank
    @Size(min = 1, max = MAX_DESCRIPTION_LENGTH)
    private String description;

    /**
     * Transaction date.
     */
    @NotNull
    private Timestamp date;

    /**
     * Purchase amount in cents.
     */
    @NotNull
    @Min(value = 1)
    private int amount;

    /**
     * Default constructor.
     */
    public PurchaseTransactionCreateDto() {
    }

    /**
     * Constructor with parameters for creating a new instance of PurchaseTransactionCreateDto.
     *
     * @param description Description of the purchase transaction.
     * @param date        Transaction date.
     * @param amount      Purchase amount in cents.
     */
    public PurchaseTransactionCreateDto(String description, Timestamp date, int amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    /**
     * Get the description of the purchase transaction.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the purchase transaction.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the transaction date.
     *
     * @return The transaction date.
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Set the transaction date.
     *
     * @param date The transaction date to set.
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Get the purchase amount in cents.
     *
     * @return The purchase amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set the purchase amount in cents.
     *
     * @param amount The purchase amount to set.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Convert the PurchaseTransactionCreateDto to a PurchaseTransaction entity.
     *
     * @return The corresponding PurchaseTransaction entity.
     */
    public PurchaseTransaction toPurchaseTransaction() {
        return new PurchaseTransaction(description, date, amount);
    }
}
