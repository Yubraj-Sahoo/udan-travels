package com.raj.travels.udan.connection_service.rest;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;

/**
 * Service interface for handling connection-related operations.
 */
public interface ConnectionRestService {
    /**
     * Fetches a connection using the provided connection credentials.
     *
     * @param connectionCredentials the credentials used to fetch the connection
     * @return a ConnectionResponse containing the connection details
     */
    ConnectionResponse fetchConnection(ConnectionCredentials connectionCredentials);
}
