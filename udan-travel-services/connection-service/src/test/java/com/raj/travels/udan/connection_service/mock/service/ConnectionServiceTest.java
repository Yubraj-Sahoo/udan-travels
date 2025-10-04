package com.raj.travels.udan.connection_service.mock.service;

import com.raj.travels.commons.converters.JsonConverter;
import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.dto.amadeus.AmadeusOAuth2TokenDto;
import com.raj.travels.udan.connection_service.exceptions.ConnectionFailedException;
import com.raj.travels.udan.connection_service.service.ConnectionService;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
    @MockitoBean
    private RestTemplate restTemplate;

    @Value("${amadeus.auth.url}")
    private String amadeusAuthUrl;

    @PreDestroy
    void resetMocks() {
        Mockito.reset(restTemplate);
    }

    /**
     * Test fetching connection response.
     */
    @Test
    void testFetchConnection() {
        AmadeusOAuth2TokenDto auth2TokenDto = JsonConverter.fromJsonFile(
                "src/test/resources/json/amadeus-authtoken-valid.json", AmadeusOAuth2TokenDto.class);
        when(restTemplate.postForObject(
                Mockito.eq(amadeusAuthUrl),
                Mockito.any(),
                Mockito.eq(AmadeusOAuth2TokenDto.class)
        )).thenReturn(auth2TokenDto);

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

    /**
     * Test fetching connection when token fetch fails.
     */
    @Test
    void testFetchConnection_WithoutToken() {
        when(restTemplate.postForObject(
                Mockito.eq(amadeusAuthUrl),
                Mockito.any(),
                Mockito.eq(AmadeusOAuth2TokenDto.class)
        )).thenReturn(null);

        Pseudocode pseudocode = Pseudocode.UDAN;
        assertThrows(ConnectionFailedException.class, () -> {
            connectionService.fetchConnection(pseudocode);
        });
    }
}
