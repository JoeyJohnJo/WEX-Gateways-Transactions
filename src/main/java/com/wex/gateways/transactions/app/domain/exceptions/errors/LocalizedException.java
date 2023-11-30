package com.wex.gateways.transactions.app.domain.exceptions.errors;

import com.wex.gateways.transactions.app.config.i18n.Localization;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * A custom runtime exception that provides localized error messages using a resource bundle.
 * This exception allows specifying a message key to retrieve a localized message for different locales.
 */
public class LocalizedException extends RuntimeException {

    // The key used to retrieve localized error messages
    private final String messageKey;

    // The locale for which the error message should be localized
    private final Locale locale;


    /**
     * Constructs a new LocalizedException with the provided message key and the default locale.
     *
     * @param messageKey The key used to retrieve localized error messages.
     */
    public LocalizedException(String messageKey) {
        this(messageKey, LocaleContextHolder.getLocale());
    }

    /**
     * Constructs a new LocalizedException with the provided message key and locale.
     *
     * @param messageKey The key used to retrieve localized error messages.
     * @param locale     The locale for which the error message should be localized.
     */
    public LocalizedException(String messageKey, Locale locale) {
        this.messageKey = messageKey;
        this.locale = locale;
    }

    /**
     * Gets the localized error message for the specified locale.
     *
     * @return The localized error message.
     */
    @Override
    public String getLocalizedMessage() {
        return Localization.getExceptionMessageForLocale(messageKey, locale);
    }
}
