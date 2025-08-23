package com.raj.travels.udan.connection_service.service;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;

/**
 * Service interface for managing connection credentials.
 * This interface defines methods to retrieve connection credentials based on connection types.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-8-2025
 */
public interface ConnectionCredentialsService {
    /**
     * Retrieves the connection credentials based on the specified connection type.
     *
     * @param connectionType the type of connection for which to retrieve the credentials
     * @return the connection credentials associated with the specified connection type
     */
    ConnectionCredentials getConnectionCredentialsByConnectionType(ConnectionType connectionType);
}
