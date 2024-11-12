package io.mxsix.phantombust.exception;

/**
 * Exception class.
 */
public class IgQueryInvalidRecordException extends RuntimeException {

    public IgQueryInvalidRecordException(String query, String error) {
        super(String.format("Query '%s' returned invalid record: %s", query, error));
    }
}
