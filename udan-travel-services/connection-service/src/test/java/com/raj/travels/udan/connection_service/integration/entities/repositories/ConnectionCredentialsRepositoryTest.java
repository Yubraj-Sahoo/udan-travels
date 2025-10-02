package com.raj.travels.udan.connection_service.integration.entities.repositories;

import com.raj.travels.commons.converters.JsonConverter;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.db.repositories.ConnectionCredentialsRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ConnectionCredentialsRepositoryTest {

    @Autowired
    private ConnectionCredentialsRepository connectionCredentialsRepository;

    /**
     * Note: These tests are disabled to prevent unintended database modifications.
     * Enable them when you need to test database interactions.
     */
    @Test
    @Disabled("Disabled due to hitting actual database. Enable when needed.")
    void testInsertCredentials() {
        ConnectionCredentials connectionCredentials = new ConnectionCredentials();
        connectionCredentials.setApiKey("");
        connectionCredentials.setApiSecret("");
        connectionCredentials.setServiceName("");
        connectionCredentials.setPseudocode(Pseudocode.UDAN);
        connectionCredentials.setCompanyId(1);

        connectionCredentialsRepository.save(connectionCredentials);

        assertNotNull(connectionCredentials);
    }

    /**
     * Note: This test is disabled to prevent unintended database modifications.
     * Enable it when you need to test database interactions.
     */
    @Test
    @Disabled("Disabled due to hitting actual database. Enable when needed.")
    void testFindByPseudocode() {
        Pseudocode pseudocode = Pseudocode.UDAN;
        ConnectionCredentials credentials = connectionCredentialsRepository.findByPseudocode(pseudocode);
        JsonConverter.toJsonFile("src/test/resources/json/connection-credentials.json", credentials);
        assertNotNull(credentials);
    }
}
