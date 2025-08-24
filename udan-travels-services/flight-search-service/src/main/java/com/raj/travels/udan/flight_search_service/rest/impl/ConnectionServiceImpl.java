package com.raj.travels.udan.flight_search_service.rest.impl;

import com.raj.travels.udan.flight_search_service.rest.ConnectionService;
import com.raj.travels.udan.travel_domain.dto.connection.AuthenticationResponse;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import com.raj.travels.udan.travel_domain.exceptions.ConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Implementation of ConnectionService to fetch authentication details.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {
    @Value("${flight.connection.url}")
    private String url;
    private final RestTemplate restTemplate;


    /**
     * Fetches authentication details.
     *
     * @return AuthenticationResponse containing authentication information.
     */
    @Override
    public String fetchAuthenticationToken(ConnectionType connectionType) {
        if (Objects.isNull(connectionType)) {
            throw new IllegalArgumentException("Connection type must not be null");
        }
        String connectionUrl = String.format("%s/%s/%s",
                url, connectionType.getGdsType().getName(), connectionType.getServiceId());
        AuthenticationResponse authenticationResponse = null;
        try {
            authenticationResponse = restTemplate.getForObject(connectionUrl, AuthenticationResponse.class);
        } catch (Exception exception) {
            log.warn("Exception occurred while fetching authentication token: {}", exception.getMessage());
        }
        if (Objects.isNull(authenticationResponse) || Objects.isNull(authenticationResponse.getSecurityToken())) {
            log.warn("Unable to fetch authentication token from URL: {} and response : {}", connectionUrl, authenticationResponse);
            throw new ConnectionException("Failed to fetch authentication token");
        }
        return authenticationResponse.getSecurityToken();
    }
}
