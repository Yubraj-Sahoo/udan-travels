package com.raj.travels.udan.flight_search_service.integration.service;

import com.raj.travels.udan.flight_search_service.rest.ConnectionService;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConnectionServiceTest {
    @Autowired
    private ConnectionService connectionService;

    @Disabled("Disabled due to external service dependency")
    @Test
    void testFetchAuthenticationToken() {
        String authToken = connectionService.fetchAuthenticationToken(ConnectionType.UDAN_TRAVELS);
        assertNotNull(authToken);
        assertFalse(authToken.isEmpty());
    }
}
