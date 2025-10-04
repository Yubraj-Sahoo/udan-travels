package com.raj.travels.udan.connection_service.mock.rest.amadeus.service;

import com.raj.travels.commons.converters.JsonConverter;
import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.dto.amadeus.AmadeusOAuth2TokenDto;
import com.raj.travels.udan.connection_service.exceptions.ConnectionFailedException;
import com.raj.travels.udan.connection_service.rest.amadeus.service.AmadeusConnectionRestService;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Test class for AmadeusConnectionRestService.
 * This class uses Mockito to mock the RestTemplate and tests the fetchConnection method.
 */
@SpringBootTest
class AmadeusConnectionRestServiceTest {
    @MockitoBean
    private RestTemplate restTemplate;
    @Autowired
    private AmadeusConnectionRestService amadeusConnectionRestService;
    @Value("${amadeus.auth.url}")
    private String amadeusAuthUrl;

    @PreDestroy
    void resetMocks() {
        Mockito.reset(restTemplate);
    }


    /**
     * Tests the fetchConnection method of the AmadeusConnectionRestService.
     * It verifies that the method correctly fetches a connection using the provided credentials
     * and maps the response to a ConnectionResponse object.
     */
    @Test
    void testFetchConnection() {
        AmadeusOAuth2TokenDto auth2TokenDto = JsonConverter.fromJsonFile(
                "src/test/resources/json/amadeus-authtoken-valid.json", AmadeusOAuth2TokenDto.class);
        ConnectionCredentials connectionCredentials = JsonConverter.fromJsonFile(
                "src/test/resources/json/connection-credentials.json", ConnectionCredentials.class);

        when(restTemplate.postForObject(
                Mockito.eq(amadeusAuthUrl),
                Mockito.any(),
                Mockito.eq(AmadeusOAuth2TokenDto.class)
        )).thenReturn(auth2TokenDto);
        ConnectionResponse connectionResponse = amadeusConnectionRestService.fetchConnection(connectionCredentials);

        assertNotNull(connectionResponse);
        assertEquals("NwZkoPiT1VbrCwFbwS86G6mw1LeO", connectionResponse.getSecurityToken());
        assertEquals(1799L, connectionResponse.getExpiresIn());
        assertEquals(Pseudocode.UDAN, connectionResponse.getPseudocode());
    }


    /**
     * Tests the fetchConnection method of the AmadeusConnectionRestService when the token fetch fails.
     * It verifies that the method throws a ConnectionFailedException when no token is returned.
     */
    @Test
    void testFetchConnection_WithoutToken() {
        ConnectionCredentials connectionCredentials = JsonConverter.fromJsonFile(
                "src/test/resources/json/connection-credentials.json", ConnectionCredentials.class);

        when(restTemplate.postForObject(
                Mockito.eq(amadeusAuthUrl),
                Mockito.any(),
                Mockito.eq(AmadeusOAuth2TokenDto.class)
        )).thenReturn(null);

        Assertions.assertThrows(ConnectionFailedException.class, () ->
                amadeusConnectionRestService.fetchConnection(connectionCredentials));
    }

    @Test
    void testFetchConnection_WithInvalidCredentials() {
        AmadeusOAuth2TokenDto auth2TokenDto = JsonConverter.fromJsonFile(
                "src/test/resources/json/amadeus-authtoken-valid.json", AmadeusOAuth2TokenDto.class);

        when(restTemplate.postForObject(
                Mockito.eq(amadeusAuthUrl),
                Mockito.any(),
                Mockito.eq(AmadeusOAuth2TokenDto.class)
        )).thenReturn(auth2TokenDto);

        Assertions.assertThrows(ConnectionFailedException.class, () ->
                amadeusConnectionRestService.fetchConnection(null));
    }
}
