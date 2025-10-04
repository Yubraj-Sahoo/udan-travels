package com.raj.travels.udan.connection_service.service.impl;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.db.repositories.ConnectionCredentialsRepository;
import com.raj.travels.udan.connection_service.rest.ConnectionRestService;
import com.raj.travels.udan.connection_service.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ConnectionService interface.
 *
 * @version 1.0
 * @since 2025-10-02
 */
@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionCredentialsRepository connectionCredentialsRepository;
    private final ConnectionRestService connectionRestService;

    /**
     * Fetches connection response based on the provided pseudocode.
     *
     * @param pseudocode the pseudocode to identify the connection
     * @return the connection response
     */
    @Override
    public ConnectionResponse fetchConnection(Pseudocode pseudocode) {
        ConnectionCredentials connectionCredentials = connectionCredentialsRepository.findByPseudocode(pseudocode);
        return connectionRestService.fetchConnection(connectionCredentials);
    }
}
