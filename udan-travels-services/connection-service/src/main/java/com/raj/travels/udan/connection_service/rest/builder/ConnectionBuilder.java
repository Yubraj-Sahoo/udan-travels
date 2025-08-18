package com.raj.travels.udan.connection_service.rest.builder;

import com.raj.travels.udan.connection_service.db.entity.ConnectionCredentials;
import org.springframework.http.HttpEntity;

/**
 * Interface for building connection requests for various GDS services.
 * This interface defines a method to create an HTTP entity that contains the necessary
 * headers and body for making a connection request.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
public interface ConnectionBuilder {
    /**
     * Builds a connection request string based on the provided connection credentials.
     *
     * @param connection the connection credentials to build the request for
     * @return the connection request string
     */
    HttpEntity<String> buildConnectionRequest(ConnectionCredentials connection);
}
