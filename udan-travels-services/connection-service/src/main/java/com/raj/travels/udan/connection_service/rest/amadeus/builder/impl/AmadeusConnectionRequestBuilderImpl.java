package com.raj.travels.udan.connection_service.rest.amadeus.builder.impl;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.rest.amadeus.builder.AmadeusConnectionRequestBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * Implementation of the AmadeusConnectionRequestBuilder interface for building Amadeus connection requests.
 * This class constructs the HTTP entity required to request a security token from the Amadeus API.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Service
public class AmadeusConnectionRequestBuilderImpl implements AmadeusConnectionRequestBuilder {
    /**
     * Builds a connection request string based on the provided connection credentials.
     *
     * @param connection the connection credentials to build the request for
     * @return the connection request string
     */
    @Override
    public HttpEntity<String> build(ConnectionCredentials connection) {
        validateConnection(connection);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String formEncodedBody = UriComponentsBuilder.newInstance()
                .queryParam("grant_type", connection.getGrantType())
                .queryParam("client_id", connection.getApiKey())
                .queryParam("client_secret", connection.getApiSecret())
                .build()
                .toUriString()
                .substring(1); // remove leading '?'

        return new HttpEntity<>(formEncodedBody, headers);
    }

    /**
     * Validates the connection credentials.
     *
     * @param connection the connection credentials to validate
     */
    private void validateConnection(ConnectionCredentials connection) {
        if (Objects.isNull(connection) || Objects.isNull(connection.getApiKey())
                || Objects.isNull(connection.getApiSecret())
                || Objects.isNull(connection.getGrantType())) {
            throw new IllegalArgumentException("Invalid connection credentials provided for Amadeus connection request.");
        }
    }
}
