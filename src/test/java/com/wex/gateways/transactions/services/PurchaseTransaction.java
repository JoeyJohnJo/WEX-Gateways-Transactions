package com.wex.gateways.transactions.services;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PurchaseTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotBlank
    @Column(length = 50)
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
