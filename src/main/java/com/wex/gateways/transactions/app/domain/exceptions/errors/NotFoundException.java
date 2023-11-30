package com.wex.gateways.transactions.app.domain.exceptions.errors;

/**
 * Exception thrown to indicate that a requested resource was not found.
 * This exception extends {@link LocalizedException} to provide localized error messages.
 */
public class NotFoundException extends LocalizedException {

    // The base name for the resource bundle key for not found exceptions
    private static final String NOT_FOUND_EXCEPTION_BASENAME = "exception.notFound";

    // The object representing the requested resource
    private final Object requestedResource;

    /**
     * Constructs a new NotFoundException with a default message key and the provided requested resource.
     *
     * @param requestedResource The object representing the requested resource.
     */
    public NotFoundException(Object requestedResource) {
        super(NOT_FOUND_EXCEPTION_BASENAME);
        this.requestedResource = requestedResource;
    }

    /**
     * Gets the object representing the requested resource.
     *
     * @return The requested resource object.
     */
    public Object getRequestedResource() {
        return requestedResource;
    }
}
