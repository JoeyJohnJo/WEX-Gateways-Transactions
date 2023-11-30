package com.wex.gateways.transactions.app.domain.database.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Entity class representing a Purchase Transaction.
 *
 * This class defines the structure of a purchase transaction with details such as description, date,
 * and amount in a specific currency. It also provides methods for convenient access and manipulation of the data.
 */
@Entity
public class PurchaseTransaction {

    /**
     * Maximum allowed length for the description field.
     */
    public static final int MAX_DESCRIPTION_LENGTH = 50;


    /**
     * Unique identifier for the purchase transaction.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Description of the purchase transaction.
     * Must not be blank and should adhere to the specified length constraints.
     */
    @NotBlank
    @Size(min = 1, max = MAX_DESCRIPTION_LENGTH)
    @Column(length = MAX_DESCRIPTION_LENGTH, nullable = false)
    private String description;

    /**
     * Timestamp representing the date and time of the purchase transaction.
     */
    @NotNull
    @Column(nullable = false)
    private Timestamp date;

    /**
     * Amount of the purchase transaction represented in US Dollar cents.
     * Must be a positive integer.
     */
    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private int amount;


    /**
     * Default constructor for JPA.
     */
    public PurchaseTransaction() {
    }

    /**
     * Constructor for creating a new purchase transaction.
     *
     * @param description Description of the purchase transaction.
     * @param date        Timestamp representing the date and time of the purchase transaction.
     * @param amount      Amount of the purchase transaction.
     */
    public PurchaseTransaction(String description, Timestamp date, int amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    /**
     * Constructor for creating a new purchase transaction with a specified identifier.
     *
     * @param id          Unique identifier for the purchase transaction.
     * @param description Description of the purchase transaction.
     * @param date        Timestamp representing the date and time of the purchase transaction.
     * @param amount      Amount of the purchase transaction.
     */
    public PurchaseTransaction(UUID id, String description, Timestamp date, int amount) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }


    /**
     * Get the unique identifier of the purchase transaction.
     *
     * @return The unique identifier.
     */
    public UUID getId() {
        return id;
    }


    /**
     * Set the unique identifier of the purchase transaction.
     *
     * @param id The unique identifier.
     */
    public void setId(UUID id) {
        this.id = id;
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
     * @param description The description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the date and time of the purchase transaction.
     *
     * @return The timestamp representing the date and time.
     */
    public Timestamp getDate() {
        return date;
    }


    /**
     * Set the date and time of the purchase transaction.
     *
     * @param date The timestamp representing the date and time.
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Get the amount of the purchase transaction in US Dollar cents.
     *
     * @return The amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set the amount of the purchase transaction in US Dollar cents.
     *
     * @param amount The amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Get the US Dollar amount equivalent of the purchase transaction.
     *
     * @return The dollar amount.
     */
    @Transient
    public BigDecimal getDollarAmount() {
        return BigDecimal.valueOf(getAmount()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
    }
}
