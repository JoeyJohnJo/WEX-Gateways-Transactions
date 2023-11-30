package com.wex.gateways.transactions.app.domain.exceptions.errors;

import com.wex.gateways.transactions.app.config.i18n.Localization;

import java.util.Locale;

public class LocalizedException extends RuntimeException {
    private final String messageKey;
    private final Locale locale;

    public LocalizedException(String messageKey) {
        this(messageKey, Locale.getDefault());
    }

    public LocalizedException(String messageKey, Locale locale) {
        this.messageKey = messageKey;
        this.locale = locale;
    }

    public String getLocalizedMessage() {
        return Localization.getExceptionMessageForLocale(messageKey, locale);
    }
}
