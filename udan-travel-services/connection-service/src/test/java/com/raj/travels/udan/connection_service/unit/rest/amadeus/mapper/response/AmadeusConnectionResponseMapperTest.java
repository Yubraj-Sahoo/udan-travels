package com.raj.travels.udan.connection_service.unit.rest.amadeus.mapper.response;


import com.raj.travels.commons.converters.JsonConverter;
import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.dto.amadeus.AmadeusOAuth2TokenDto;
import com.raj.travels.udan.connection_service.exceptions.ConnectionFailedException;
import com.raj.travels.udan.connection_service.rest.amadeus.mappers.response.AmadeusConnectionResponseMapper;
import com.raj.travels.udan.connection_service.rest.amadeus.mappers.response.impl.AmadeusConnectionResponseMapperImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the AmadeusConnectionResponseMapper class.
 */
class AmadeusConnectionResponseMapperTest {
    private final AmadeusConnectionResponseMapper amadeusConnectionResponseMapper =
            new AmadeusConnectionResponseMapperImpl();

    /**
     * Tests the evaluate method of the AmadeusConnectionResponseMapper.
     * It verifies that the method correctly maps an AmadeusOAuth2TokenDto
     * and ConnectionCredentials to a ConnectionResponse.
     */
    @Test
    void testEvaluate() {
        AmadeusOAuth2TokenDto auth2TokenDto = JsonConverter.fromJsonFile(
                "src/test/resources/json/amadeus-authtoken-valid.json", AmadeusOAuth2TokenDto.class);
        ConnectionCredentials connectionCredentials = JsonConverter.fromJsonFile(
                "src/test/resources/json/connection-credentials.json", ConnectionCredentials.class);

        ConnectionResponse connectionResponse =
                amadeusConnectionResponseMapper.evaluate(auth2TokenDto, connectionCredentials);

        assertNotNull(connectionResponse);
        assertEquals("NwZkoPiT1VbrCwFbwS86G6mw1LeO", connectionResponse.getSecurityToken());
        assertEquals(1799L, connectionResponse.getExpiresIn());
        assertEquals(Pseudocode.UDAN, connectionResponse.getPseudocode());
    }

    /**
     * Tests the evaluate method of the AmadeusConnectionResponseMapper with a null token.
     * It verifies that the method throws a ConnectionFailedException when the token is null.
     */
    @Test
    void testEvaluateWithNullToken() {
        ConnectionCredentials connectionCredentials = JsonConverter.fromJsonFile(
                "src/test/resources/json/connection-credentials.json", ConnectionCredentials.class);

        assertThrows(ConnectionFailedException.class, () ->
                amadeusConnectionResponseMapper.evaluate(null, connectionCredentials));
    }
}
