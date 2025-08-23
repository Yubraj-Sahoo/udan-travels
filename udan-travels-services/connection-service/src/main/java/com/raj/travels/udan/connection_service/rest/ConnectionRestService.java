package com.raj.travels.udan.connection_service.rest;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.travel_domain.domain.connection.AuthenticationDetails;

/**
 * Interface for security services related to connection credentials.
 * This interface defines a method to retrieve the security token associated with a given connection's credentials.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
public interface ConnectionRestService {

    /**
     * Retrieves the security details for a given connection credentials.
     *
     * @param connectionCredentials the credentials of the connection for which to retrieve the security details
     * @return the security details associated with the specified connection credentials
     */
    AuthenticationDetails getSecurityDetails(ConnectionCredentials connectionCredentials);
}
