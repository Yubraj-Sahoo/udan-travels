package com.raj.travels.udan.connection_service.service;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.Pseudocode;

/**
 * Implementation of the ConnectionService interface.
 *
 * @version 1.0
 * @since 2025-10-02
 */
public interface ConnectionService {
    /**
     * Fetches connection response based on the provided pseudocode.
     *
     * @param pseudocode the pseudocode to identify the connection
     * @return the connection response
     */
    ConnectionResponse fetchConnection(Pseudocode pseudocode);
}
