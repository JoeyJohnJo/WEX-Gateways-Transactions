package com.wex.gateways.transactions.app.controllers;

import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionCreateDto;
import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionCurrencyConvertedDetailsDto;
import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionDetailsDto;
import com.wex.gateways.transactions.app.domain.services.PurchaseTransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller class for managing purchase transactions.
 *
 * This controller provides endpoints for creating and retrieving details of purchase transactions,
 * including the ability to retrieve transaction details in a specific currency after conversion.
 */
@RestController
@RequestMapping("/transactions")
public class PurchaseTransactionController {
    private final PurchaseTransactionService purchaseTransactionService;

    /**
     * Constructor for PurchaseTransactionController.
     *
     * @param purchaseTransactionService Service for managing purchase transactions.
     */
    public PurchaseTransactionController(PurchaseTransactionService purchaseTransactionService) {
        this.purchaseTransactionService = purchaseTransactionService;
    }

    /**
     * Endpoint for creating a new purchase transaction.
     *
     * @param dto The data transfer object containing details of the purchase transaction.
     * @return ResponseEntity containing the details of the stored purchase transaction.
     */
    @PostMapping
    public ResponseEntity<PurchaseTransactionDetailsDto> storePurchaseTransaction(
        @Valid @RequestBody PurchaseTransactionCreateDto dto) {
        return ResponseEntity.ok(
            PurchaseTransactionDetailsDto.from(
                purchaseTransactionService.storePurchaseTransaction(dto.toPurchaseTransaction())
            )
        );
    }

    /**
     * Endpoint for retrieving details of a purchase transaction in a specific currency after conversion.
     *
     * @param id       The unique identifier of the purchase transaction.
     * @param currency The target currency for conversion.
     * @return ResponseEntity containing details of the purchase transaction in the specified currency.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseTransactionCurrencyConvertedDetailsDto> getPurchaseTransactionInCurrency(
        @PathVariable UUID id, @RequestParam String currency) {
        return ResponseEntity.ok(purchaseTransactionService.getPurchaseTransactionInCurrency(id, currency));
    }
}
