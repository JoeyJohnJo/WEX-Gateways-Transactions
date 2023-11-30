package com.wex.gateways.transactions.app.domain.database.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class PurchaseTransaction {
    public static final int MAX_DESCRIPTION_LENGTH = 50;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 1, max = MAX_DESCRIPTION_LENGTH)
    @Column(length = MAX_DESCRIPTION_LENGTH, nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Timestamp date;

    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private int amount;

    public PurchaseTransaction() {
    }

    public PurchaseTransaction(String description, Timestamp date, int amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public PurchaseTransaction(UUID id, String description, Timestamp date, int amount) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
