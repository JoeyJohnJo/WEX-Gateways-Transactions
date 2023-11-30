package com.wex.gateways.transactions.app.domain.exceptions.errors;

public class NotFoundException extends LocalizedException {

    private static final String NOT_FOUND_EXCEPTION_BASENAME = "exception.notFound";
    private final Object requestedResource;

    public NotFoundException(Object requestedResource) {
        super(NOT_FOUND_EXCEPTION_BASENAME);
        this.requestedResource = requestedResource;
    }

    public Object getRequestedResource() {
        return requestedResource;
    }
}
