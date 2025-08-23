package com.raj.travels.udan.connection_service.rest.amadeus.evaluator.impl;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.dto.AmadeusTokenResponse;
import com.raj.travels.udan.connection_service.rest.amadeus.evaluator.AmadeusConnectionResponseEvaluator;
import com.raj.travels.udan.travel_domain.domain.connection.AuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
public class AmadeusConnectionResponseEvaluatorImpl implements AmadeusConnectionResponseEvaluator {
    /**
     * Evaluates the Amadeus token response and connection credentials to produce authentication details.
     *
     * @param amadeusTokenResponse  the response containing the Amadeus token information
     * @param connectionCredentials the credentials of the connection
     * @return the authentication details derived from the token response and credentials
     */
    @Override
    public AuthenticationDetails evaluate(AmadeusTokenResponse amadeusTokenResponse, ConnectionCredentials connectionCredentials) {
        return AuthenticationDetails.builder()
                .type(amadeusTokenResponse.getType())
                .userName(amadeusTokenResponse.getUsername())
                .connectionType(connectionCredentials.getConnectionType())
                .securityToken(amadeusTokenResponse.getAccessToken())
                .applicationName(amadeusTokenResponse.getApplicationName())
                .expiresIn(amadeusTokenResponse.getExpiresIn())
                .state(amadeusTokenResponse.getState())
                .build();
    }
}
