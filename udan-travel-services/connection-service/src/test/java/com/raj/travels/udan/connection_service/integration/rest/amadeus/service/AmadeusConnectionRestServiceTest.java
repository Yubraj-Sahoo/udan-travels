package com.raj.travels.udan.connection_service.integration.rest.amadeus.service;

import com.raj.travels.commons.converters.JsonConverter;
import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.rest.amadeus.service.AmadeusConnectionRestService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Service class for handling Amadeus connection-related operations.
 */
@SpringBootTest
class AmadeusConnectionRestServiceTest {
    @Autowired
    private AmadeusConnectionRestService amadeusConnectionRestService;

    @Test
    @Disabled("Disabled until the Amadeus API credentials are set up")
    void testFetchConnection(){
        ConnectionCredentials credentials = JsonConverter.fromJsonFile(
                "src/test/resources/json/connection-credentials.json", ConnectionCredentials.class);

        ConnectionResponse connectionResponse = amadeusConnectionRestService.fetchConnection(credentials);

        assertNotNull(connectionResponse);
    }
}
