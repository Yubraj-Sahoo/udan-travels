package com.raj.travels.udan.connection_service.service;

import com.raj.travels.udan.travel_domain.dto.connection.AuthenticationResponse;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;

/**
 * Marker interface for connection services.
 * This interface can be implemented by various connection service classes
 * to provide a common type for all connection-related services.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-8-2025
 */
public interface ConnectionService {
    /**
     * Retrieves the authentication response for a given connection type.
     *
     * @param connectionType the type of connection for which to retrieve the authentication response
     * @return the authentication response containing details such as tokens and credentials
     */
    AuthenticationResponse getConnection(ConnectionType connectionType);
}
