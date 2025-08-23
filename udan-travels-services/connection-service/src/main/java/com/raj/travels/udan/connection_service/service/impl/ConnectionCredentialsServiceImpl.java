package com.raj.travels.udan.connection_service.service.impl;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.db.repositories.ConnectionCredentialsRepository;
import com.raj.travels.udan.connection_service.service.ConnectionCredentialsService;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service implementation for managing connection credentials.
 * This class provides methods to retrieve connection credentials based on connection types.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-8-2025
 */
@Service
@RequiredArgsConstructor
public class ConnectionCredentialsServiceImpl implements ConnectionCredentialsService {
    private final ConnectionCredentialsRepository connectionCredentialsRepository;

    /**
     * Retrieves the connection credentials based on the specified connection type.
     *
     * @param connectionType the type of connection for which to retrieve the credentials
     * @return the connection credentials associated with the specified connection type
     */
    @Override
    public ConnectionCredentials getConnectionCredentialsByConnectionType(ConnectionType connectionType) {
        if (Objects.isNull(connectionType)) {
            throw new IllegalArgumentException("Connection type must not be null.");
        }
        return connectionCredentialsRepository.findByGdsTypeAndConnectionType(connectionType.getGdsType(), connectionType);
    }
}
