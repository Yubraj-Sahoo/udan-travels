package com.raj.travels.udan.connection_service.integration.db.repository;

import com.raj.travels.udan.connection_service.db.entity.ConnectionCredentials;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import com.raj.travels.udan.travel_domain.enums.connection.GdsType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ConnectionCredentialsRepositoryTest {

    @Autowired
    private ConnectionCredentialsRepository connectionCredentialsRepository;

    @Test
    @Disabled("Due to connected with original database, this test is disabled")
    void testFindByGdsTypeAndConnectionType() {
        ConnectionType connectionType = ConnectionType.AMADEUS_TEST;
        GdsType gdsType = GdsType.AMADEUS;

        ConnectionCredentials connectionCredentials = connectionCredentialsRepository.findByGdsTypeAndConnectionType(gdsType, connectionType);

        assertNotNull(connectionCredentials, "ConnectionCredentials should not be null");
        assertNotNull(connectionCredentials.getGrandType(), "GrandType should not be null");
        assertNotNull(connectionCredentials.getApiKey(), "API Key should not be null");
        assertNotNull(connectionCredentials.getApiSecret(), "API secret should not be null");
    }

    @Test
    @Disabled("Due to connected with original database, this test is disabled")
    void testInsertConnectionCredentials() {
        ConnectionCredentials connectionCredentials = new ConnectionCredentials();

        connectionCredentials.setConnectionType(ConnectionType.AMADEUS_TEST);
        connectionCredentials.setGdsType(GdsType.AMADEUS);
        connectionCredentials.setGrandType("");
        connectionCredentials.setApiKey("");
        connectionCredentials.setApiSecret("");

        connectionCredentialsRepository.save(connectionCredentials);

        assertNotNull(connectionCredentials);
    }
}
