package com.wex.gateways.transactions.app.domain.exceptions;

/**
 * Record representing the response body for errors caught by the controller advice.
 * It contains information about the error message and optional data.
 */
public record ErrorResponseBody(Object data, String message) {
}
