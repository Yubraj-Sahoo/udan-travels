package com.raj.travels.udan.connection_service.rest.amadeus.service.impl;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.dto.AmadeusTokenResponse;
import com.raj.travels.udan.connection_service.exception.AmadeusConnectionException;
import com.raj.travels.udan.connection_service.rest.amadeus.builder.AmadeusConnectionRequestBuilder;
import com.raj.travels.udan.connection_service.rest.amadeus.evaluator.AmadeusConnectionResponseEvaluator;
import com.raj.travels.udan.connection_service.rest.amadeus.service.AmadeusConnectionRestService;
import com.raj.travels.udan.travel_domain.domain.connection.AuthenticationDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Interface for security services related to connection credentials.
 * This interface defines a method to retrieve the security token associated with a given connection's credentials.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AmadeusConnectionRestServiceImpl implements AmadeusConnectionRestService {
    @Value("${gds.amadeus.security.token.url}")
    private String amadeusConnectionUrl;

    private final RestTemplate restTemplate;
    private final AmadeusConnectionRequestBuilder amadeusConnectionRequestBuilder;
    private final AmadeusConnectionResponseEvaluator amadeusConnectionResponseEvaluator;

    /**
     * Retrieves the security details for a given connection credentials.
     *
     * @param connectionCredentials the credentials of the connection for which to retrieve the security details
     * @return the security details associated with the specified connection credentials
     */
    @Override
    public AuthenticationDetails getSecurityDetails(ConnectionCredentials connectionCredentials) {
        HttpEntity<String> request = amadeusConnectionRequestBuilder.build(connectionCredentials);
        AmadeusTokenResponse amadeusTokenResponse = null;
        try {
            amadeusTokenResponse = restTemplate.postForObject(amadeusConnectionUrl, request, AmadeusTokenResponse.class);
        } catch (Exception exception) {
            log.warn("impl-connection :: Exception occurred while fetching amadeus token : {}", exception.getMessage(), exception);
        }
        if (amadeusTokenResponse == null) {
            log.error("impl-connection :: Amadeus token response is null");
            throw new AmadeusConnectionException("Amadeus token response is null");
        }
        return amadeusConnectionResponseEvaluator.evaluate(amadeusTokenResponse, connectionCredentials);
    }
}
