package com.raj.travels.udan.flight_search_service.mock.rest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.raj.travels.udan.flight_search_service.rest.ConnectionService;
import com.raj.travels.udan.travel_domain.dto.connection.AuthenticationResponse;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import com.raj.travels.udan.travel_domain.exceptions.ConnectionException;
import com.raj.travels.udan.travel_domain.util.JsonUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConnectionServiceTest {
    @MockitoBean
    private RestTemplate restTemplate;
    @Autowired
    private ConnectionService connectionService;

    @Test
    void testFetchAuthenticationToken_Valid() {
        AuthenticationResponse authenticationResponse = JsonUtils.readFromJsonFile(
                "src/test/resources/json/connection-valid.json", new TypeReference<AuthenticationResponse>() {
                });
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(authenticationResponse);

        String token = connectionService.fetchAuthenticationToken(ConnectionType.UDAN_TRAVELS);
        assertEquals("Vfa2OEDApJZq7ul39JDHQWRqzAlN", token);
    }

    @Test
    void testFetchAuthenticationToken_Error() {
        AuthenticationResponse authenticationResponse = JsonUtils.readFromJsonFile(
                "src/test/resources/json/connection-error.json", new TypeReference<AuthenticationResponse>() {
                });
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(authenticationResponse);

        assertThrows(ConnectionException.class, () ->
                connectionService.fetchAuthenticationToken(ConnectionType.UDAN_TRAVELS)
        );
    }

    @Test
    void testFetchAuthenticationToken_NullResponse() {
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(null);

        assertThrows(ConnectionException.class, () ->
                connectionService.fetchAuthenticationToken(ConnectionType.UDAN_TRAVELS)
        );
    }

    @Test
    void testFetchAuthenticationToken_NullConnectionType() {
        assertThrows(IllegalArgumentException.class, () ->
                connectionService.fetchAuthenticationToken(null)
        );
    }
}
