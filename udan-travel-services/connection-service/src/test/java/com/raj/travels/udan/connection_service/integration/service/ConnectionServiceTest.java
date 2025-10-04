package com.raj.travels.udan.connection_service.integration.service;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.exceptions.ConnectionFailedException;
import com.raj.travels.udan.connection_service.service.ConnectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration tests for ConnectionService.
 *
 * @version 1.0
 * @since 2025-10-02
 */
@SpringBootTest
class ConnectionServiceTest {
    @Autowired
    private ConnectionService connectionService;

    /**
     * Test fetching connection response.
     */
    @Test
    void testFetchConnection() {
        Pseudocode pseudocode = Pseudocode.UDAN;
        ConnectionResponse connectionResponse = connectionService.fetchConnection(pseudocode);

        assertNotNull(connectionResponse);
        assertNotNull(connectionResponse.getSecurityToken());
        assertNotNull(connectionResponse.getPseudocode());
        assertNotNull(connectionResponse.getExpiresIn());
    }

    /**
     * Test fetching connection with invalid pseudocode.
     */
    @Test
    void testFetchConnectionInvalidPseudocode() {
        assertThrows(ConnectionFailedException.class, () -> {
            connectionService.fetchConnection(null);
        });
    }
}
