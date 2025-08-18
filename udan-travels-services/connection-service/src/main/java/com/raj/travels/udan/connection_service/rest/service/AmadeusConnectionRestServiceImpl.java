package com.raj.travels.udan.connection_service.rest.service;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.rest.builder.AmadeusConnectionRequestBuilder;
import com.raj.travels.udan.travel_domain.domain.connection.AuthenticationDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * Interface for security services related to connection credentials.
 * This interface defines a method to retrieve the security token associated with a given connection's credentials.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@RequiredArgsConstructor
public class AmadeusConnectionRestServiceImpl implements ConnectionRestService {
    @Value("${gds.amadeus.security.token.url}")
    private String amadeusConnectionUrl;
    private final AmadeusConnectionRequestBuilder amadeusConnectionRequestBuilder;

    /**
     * Retrieves the security details for a given connection credentials.
     *
     * @param connectionCredentials the credentials of the connection for which to retrieve the security details
     * @return the security details associated with the specified connection credentials
     */
    @Override
    public AuthenticationDetails getSecurityDetails(ConnectionCredentials connectionCredentials) {
        return null;
    }
}
