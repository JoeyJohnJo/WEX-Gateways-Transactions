package com.wex.gateways.transactions.app.domain.services;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import com.wex.gateways.transactions.app.domain.database.repositories.PurchaseTransactionRepository;
import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionCurrencyConvertedDetailsDto;
import com.wex.gateways.transactions.app.domain.exceptions.errors.InvalidValueException;
import com.wex.gateways.transactions.app.domain.exceptions.errors.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction.MAX_DESCRIPTION_LENGTH;

@Service
public class PurchaseTransactionServiceImpl implements PurchaseTransactionService {

    private final PurchaseTransactionRepository purchaseTransactionRepository;
    private final FetchExchangeRate fetchExchangeRate;

    public PurchaseTransactionServiceImpl(
        PurchaseTransactionRepository purchaseTransactionRepository,
        FetchExchangeRate fetchExchangeRate) {
        this.purchaseTransactionRepository = purchaseTransactionRepository;
        this.fetchExchangeRate = fetchExchangeRate;
    }

    @Override
    public PurchaseTransaction storePurchaseTransaction(@Valid PurchaseTransaction purchaseTransaction) {
        assertPurchaseTransactionIsValid(purchaseTransaction);
        return purchaseTransactionRepository.save(purchaseTransaction);
    }

    @Override
    public PurchaseTransactionCurrencyConvertedDetailsDto getPurchaseTransactionInCurrency(UUID id, String currency) {
        var transaction = getPurchaseTransactionById(id);
        var exchangeRate = fetchExchangeRate.fetchExchangeRate(currency, transaction.getDate());
        return new PurchaseTransactionCurrencyConvertedDetailsDto(
            transaction.getId(),
            transaction.getDescription(),
            transaction.getDate(),
            transaction.getDollarAmount(),
            transaction.getAmount(),
            exchangeRate.currency(),
            exchangeRate.exchangeRate(),
            transaction.getDollarAmount().multiply(exchangeRate.exchangeRate())
        );
    }

    private PurchaseTransaction getPurchaseTransactionById(UUID id) {
        return purchaseTransactionRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    private void assertPurchaseTransactionIsValid(PurchaseTransaction purchaseTransaction) {
        if (purchaseTransaction.getId() != null)
            throw new InvalidValueException(purchaseTransaction.getId());

        if (purchaseTransaction.getDescription().trim().length() > MAX_DESCRIPTION_LENGTH)
            throw new InvalidValueException(purchaseTransaction.getDescription());

        if (purchaseTransaction.getDate() == null || purchaseTransaction.getDate().after(Timestamp.from(Instant.now())))
            throw new InvalidValueException(purchaseTransaction.getDate());

        if (purchaseTransaction.getAmount() <= 0)
            throw new InvalidValueException(purchaseTransaction.getAmount());
    }
}
