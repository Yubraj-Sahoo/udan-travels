package com.raj.travels.udan.connection_service.rest.amadeus.mappers.request.impl;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.exceptions.ConnectionFailedException;
import com.raj.travels.udan.connection_service.rest.amadeus.mappers.request.AmadeusConnectionRequestMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.raj.travels.udan.connection_service.constants.StringConstant.*;

/**
 * Implementation of the AmadeusConnectionRequestMapper interface.
 */
@Service
public class AmadeusConnectionRequestMapperImpl implements AmadeusConnectionRequestMapper {

    /**
     * Builds an HTTP request entity using the provided connection credentials.
     *
     * @param connectionCredentials the credentials used to build the request
     * @return an HttpEntity containing the request data
     */
    @Override
    public HttpEntity<MultiValueMap<String, String>> build(ConnectionCredentials connectionCredentials) {
        if (validateConnectionCredentials(connectionCredentials)) {
            throw new ConnectionFailedException("Invalid connection credentials provided.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(AMADEUS_GRANT_TYPE, connectionCredentials.getServiceName());
        body.add(AMADEUS_CLIENT_ID, connectionCredentials.getApiKey());
        body.add(AMADEUS_CLIENT_SECRET, connectionCredentials.getApiSecret());

        return new HttpEntity<>(body, headers);
    }

    /**
     * Validates the provided connection credentials.
     *
     * @param connectionCredentials the credentials to validate
     * @return true if any of the required fields are null, false otherwise
     */
    private static boolean validateConnectionCredentials(ConnectionCredentials connectionCredentials) {
        return connectionCredentials == null || connectionCredentials.getApiKey() == null ||
                connectionCredentials.getApiSecret() == null || connectionCredentials.getServiceName() == null;
    }
}
