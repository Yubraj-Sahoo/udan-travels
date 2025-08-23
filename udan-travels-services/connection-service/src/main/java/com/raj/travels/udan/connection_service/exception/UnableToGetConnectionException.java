package com.raj.travels.udan.connection_service.exception;

/**
 * Exception thrown when unable to get a connection for the specified connection type.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-08-2025
 */
public class UnableToGetConnectionException extends RuntimeException {
    public UnableToGetConnectionException(String s) {
        super(s);
    }
}
