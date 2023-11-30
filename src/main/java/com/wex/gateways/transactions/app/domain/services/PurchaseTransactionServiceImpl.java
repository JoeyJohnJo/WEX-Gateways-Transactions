package com.wex.gateways.transactions.app.domain.services;

import com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction;
import com.wex.gateways.transactions.app.domain.database.repositories.PurchaseTransactionRepository;
import com.wex.gateways.transactions.app.domain.dtos.PurchaseTransactionCurrencyConvertedDetailsDto;
import com.wex.gateways.transactions.app.domain.exceptions.errors.ExchangeRateNotAvailableException;
import com.wex.gateways.transactions.app.domain.exceptions.errors.InvalidValueException;
import com.wex.gateways.transactions.app.domain.exceptions.errors.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import static com.wex.gateways.transactions.app.domain.database.entities.PurchaseTransaction.MAX_DESCRIPTION_LENGTH;

/**
 * Service implementation for managing purchase transactions.
 *
 * This service provides methods for storing purchase transactions and retrieving
 * details of a purchase transaction in a specific currency after converting it
 * using the latest available exchange rate.
 */
@Service
public class PurchaseTransactionServiceImpl implements PurchaseTransactionService {

    private final PurchaseTransactionRepository purchaseTransactionRepository;
    private final FetchExchangeRate fetchExchangeRate;

    /**
     * Constructor for PurchaseTransactionServiceImpl.
     *
     * @param purchaseTransactionRepository Repository for managing purchase transactions.
     * @param fetchExchangeRate             Service for fetching exchange rates.
     */
    public PurchaseTransactionServiceImpl(
        PurchaseTransactionRepository purchaseTransactionRepository,
        FetchExchangeRate fetchExchangeRate) {
        this.purchaseTransactionRepository = purchaseTransactionRepository;
        this.fetchExchangeRate = fetchExchangeRate;
    }


    /**
     * Store a purchase transaction.
     *
     * @param purchaseTransaction The purchase transaction to be stored.
     * @return The stored purchase transaction.
     * @throws InvalidValueException If the purchase transaction contains invalid values.
     */
    @Override
    public PurchaseTransaction storePurchaseTransaction(@Valid PurchaseTransaction purchaseTransaction) {
        assertPurchaseTransactionIsValid(purchaseTransaction);
        return purchaseTransactionRepository.save(purchaseTransaction);
    }

    /**
     * Get details of a purchase transaction in a specific currency after conversion.
     *
     * @param id       The unique identifier of the purchase transaction.
     * @param currency The target currency for conversion.
     * @return Details of the purchase transaction in the specified currency.
     * @throws NotFoundException              If the purchase transaction with the given ID is not found.
     * @throws ExchangeRateNotAvailableException If the exchange rate for the specified currency is not available.
     */
    @Override
    public PurchaseTransactionCurrencyConvertedDetailsDto getPurchaseTransactionInCurrency(UUID id, String currency) {
        var transaction = getPurchaseTransactionById(id);
        var exchangeRate = fetchExchangeRate.fetchExchangeRate(currency, transaction.getDate());
        assertExchangeRateDateIsWithinSixMonthsOfPurchaseDate(exchangeRate.date(), transaction.getDate());
        return new PurchaseTransactionCurrencyConvertedDetailsDto(
            transaction.getId(),
            transaction.getDescription(),
            transaction.getDate(),
            transaction.getDollarAmount(),
            transaction.getAmount(),
            exchangeRate.currency(),
            exchangeRate.exchangeRate(),
            transaction.getDollarAmount().multiply(exchangeRate.exchangeRate()).setScale(2, RoundingMode.HALF_EVEN)
        );
    }

    /**
     * Get a purchase transaction by its unique identifier.
     *
     * @param id The unique identifier of the purchase transaction.
     * @return The purchase transaction.
     * @throws NotFoundException If the purchase transaction with the given ID is not found.
     */
    private PurchaseTransaction getPurchaseTransactionById(UUID id) {
        return purchaseTransactionRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    /**
     * Assert the validity of a purchase transaction.
     *
     * @param purchaseTransaction The purchase transaction to be validated.
     * @throws InvalidValueException If the purchase transaction contains invalid values.
     */
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

    /**
     * Assert that the exchange rate date is within six months of the purchase date.
     *
     * @param exchangeRateDate The date of the exchange rate.
     * @param purchaseDate     The date of the purchase transaction.
     * @throws ExchangeRateNotAvailableException If the exchange rate is not available within the specified period.
     */
    private void assertExchangeRateDateIsWithinSixMonthsOfPurchaseDate(Timestamp exchangeRateDate, Timestamp purchaseDate) {
        LocalDate exchangeRateLocalDate = convertToLocalDate(exchangeRateDate);
        LocalDate purchaseLocalDate = convertToLocalDate(purchaseDate);
        LocalDate sixMonthsAgo = purchaseLocalDate.minusMonths(6);

        if (exchangeRateLocalDate.isBefore(sixMonthsAgo) || exchangeRateLocalDate.isAfter(purchaseLocalDate))
            throw new ExchangeRateNotAvailableException();
    }

    /**
     * Convert a Timestamp to a LocalDate.
     *
     * @param timestamp The Timestamp to be converted.
     * @return The corresponding LocalDate.
     */
    private LocalDate convertToLocalDate(Timestamp timestamp) {
        Instant instant = timestamp.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
