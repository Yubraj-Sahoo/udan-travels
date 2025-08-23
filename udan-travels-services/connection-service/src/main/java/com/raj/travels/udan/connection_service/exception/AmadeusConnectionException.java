package com.raj.travels.udan.connection_service.exception;

/**
 * Custom exception class for handling connection-related errors with Amadeus API.
 * This exception is thrown when there are issues establishing or maintaining a connection
 * to the Amadeus services.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-8-2025
 */
public class AmadeusConnectionException extends RuntimeException {
    public AmadeusConnectionException(String message) {
        super(message);
    }
}
