package com.wex.gateways.transactions.app.config.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    public static final String EXCEPTIONS_BUNDLE_BASE_NAME = "i18n/exceptions/exceptions";

    public static String getMessageForLocale(String baseName, String messageKey, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale)
            .getString(messageKey);
    }

    public static String getExceptionMessageForLocale(String messageKey, Locale locale) {
        return getMessageForLocale(EXCEPTIONS_BUNDLE_BASE_NAME, messageKey, locale);
    }
}
