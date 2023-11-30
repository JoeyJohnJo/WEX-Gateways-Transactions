package com.wex.gateways.transactions.app.controllers;

import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionCreateDto;
import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionCurrencyConvertedDetailsDto;
import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionDetailsDto;
import com.wex.gateways.transactions.app.domain.services.PurchaseTransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class PurchaseTransactionController {
    private final PurchaseTransactionService purchaseTransactionService;

    public PurchaseTransactionController(PurchaseTransactionService purchaseTransactionService) {
        this.purchaseTransactionService = purchaseTransactionService;
    }

    @PostMapping
    public ResponseEntity<PurchaseTransactionDetailsDto> storePurchaseTransaction(
        @Valid @RequestBody PurchaseTransactionCreateDto dto) {
        return ResponseEntity.ok(
            PurchaseTransactionDetailsDto.from(
                purchaseTransactionService.storePurchaseTransaction(dto.toPurchaseTransaction())
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseTransactionCurrencyConvertedDetailsDto> getPurchaseTransactionInCurrency(
        @PathVariable UUID id, @RequestParam String currency) {
        return ResponseEntity.ok(purchaseTransactionService.getPurchaseTransactionInCurrency(id, currency));
    }
}
