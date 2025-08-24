package com.raj.travels.udan.connection_service.integration.rest.amadeus.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.rest.amadeus.service.AmadeusConnectionRestService;
import com.raj.travels.udan.travel_domain.domain.connection.AuthenticationDetails;
import com.raj.travels.udan.travel_domain.util.JsonUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AmadeusConnectionRestServiceTest {

    @Autowired
    private AmadeusConnectionRestService amadeusConnectionRestService;

    @Test
    @Disabled("Due to it is hitting actual amadeus service, this test is disabled")
    void testGetAmadeusToken() {
        ConnectionCredentials connectionCredentials = JsonUtils.readFromJsonFile("src/test/resources/json/connectionCredentials.json", new TypeReference<ConnectionCredentials>() {
        });
        assertNotNull(connectionCredentials, "ConnectionCredentials should not be null");

        AuthenticationDetails authenticationDetails = amadeusConnectionRestService.getSecurityDetails(connectionCredentials);

        assertNotNull(authenticationDetails);
        assertNotNull(authenticationDetails.getSecurityToken());
        assertFalse(authenticationDetails.getSecurityToken().isEmpty());
    }
}