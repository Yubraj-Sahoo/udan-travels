package com.raj.travels.udan.connection_service.service.impl;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.exception.UnableToGetConnectionException;
import com.raj.travels.udan.connection_service.mapper.AuthenticationMapper;
import com.raj.travels.udan.connection_service.rest.ConnectionRestService;
import com.raj.travels.udan.connection_service.service.ConnectionCredentialsService;
import com.raj.travels.udan.connection_service.service.ConnectionService;
import com.raj.travels.udan.travel_domain.domain.connection.AuthenticationDetails;
import com.raj.travels.udan.travel_domain.dto.connection.AuthenticationResponse;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ConnectionService interface for managing connections to various GDS services.
 * This service retrieves authentication tokens and connection details based on the specified connection type.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-8-2025
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRestService connectionRestService;
    private final ConnectionCredentialsService connectionCredentialsService;

    /**
     * Retrieves the authentication response for a given connection type.
     *
     * @param connectionType the type of connection for which to retrieve the authentication response
     * @return the authentication response containing details such as tokens and credentials
     */
    @Override
    public AuthenticationResponse getConnection(ConnectionType connectionType) {
        try {
            ConnectionCredentials connectionCredentials = connectionCredentialsService
                    .getConnectionCredentialsByConnectionType(connectionType);

            AuthenticationDetails authenticationDetails = connectionRestService.getSecurityDetails(connectionCredentials);
            return AuthenticationMapper.mapToAuthenticationResponse(authenticationDetails);
        } catch (Exception exception) {
            log.error("Unable to get connection for the given connection type: {}", connectionType, exception);
            throw new UnableToGetConnectionException(exception.getMessage());
        }
    }
}
