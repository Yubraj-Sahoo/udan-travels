package com.raj.travels.udan.connection_service.rest.amadeus.evaluator;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.dto.AmadeusTokenResponse;
import com.raj.travels.udan.travel_domain.domain.connection.AuthenticationDetails;

/**
 * Interface for evaluating Amadeus connection responses.
 * This interface defines a method to evaluate the response received from the Amadeus API
 * and extract the necessary authentication details.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
public interface AmadeusConnectionResponseEvaluator {
    /**
     * Evaluates the Amadeus token response and connection credentials to produce authentication details.
     *
     * @param amadeusTokenResponse  the response containing the Amadeus token information
     * @param connectionCredentials the credentials of the connection
     * @return the authentication details derived from the token response and credentials
     */
    AuthenticationDetails evaluate(AmadeusTokenResponse amadeusTokenResponse, ConnectionCredentials connectionCredentials);
}
