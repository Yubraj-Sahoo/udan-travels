package com.raj.travels.udan.connection_service.unit.rest.amadeus.mapper.request;

import com.raj.travels.commons.converters.JsonConverter;
import com.raj.travels.udan.connection_service.constants.StringConstant;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.exceptions.ConnectionFailedException;
import com.raj.travels.udan.connection_service.rest.amadeus.mappers.request.AmadeusConnectionRequestMapper;
import com.raj.travels.udan.connection_service.rest.amadeus.mappers.request.impl.AmadeusConnectionRequestMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

class AmadeusConnectionRequestMapperTest {
    AmadeusConnectionRequestMapper amadeusConnectionRequestMapper = new AmadeusConnectionRequestMapperImpl();

    @Test
    void testBuild() {
        ConnectionCredentials credentials = JsonConverter.fromJsonFile(
                "src/test/resources/json/connection-credentials.json", ConnectionCredentials.class);

        HttpEntity<MultiValueMap<String, String>> multiValueMapHttpEntity =
                amadeusConnectionRequestMapper.build(credentials);

        assertNotNull(multiValueMapHttpEntity);

        HttpHeaders headers = multiValueMapHttpEntity.getHeaders();
        assertEquals(MediaType.APPLICATION_FORM_URLENCODED, headers.getContentType());

        MultiValueMap<String, String> body = multiValueMapHttpEntity.getBody();
        assertNotNull(body);

        assertEquals(3, body.size());
        assertEquals(credentials.getServiceName(), body.getFirst(StringConstant.AMADEUS_GRANT_TYPE));
        assertEquals(credentials.getApiKey(), body.getFirst(StringConstant.AMADEUS_CLIENT_ID));
        assertEquals(credentials.getApiSecret(), body.getFirst(StringConstant.AMADEUS_CLIENT_SECRET));
    }

    @Test
    void testBuild_WithNullCredentials() {
        assertThrows(ConnectionFailedException.class, () ->
                amadeusConnectionRequestMapper.build(null));
    }

    @Test
    void testBuild_WithMissingApiKey() {
        ConnectionCredentials credentials = new ConnectionCredentials();

        assertThrows(ConnectionFailedException.class, () ->
                amadeusConnectionRequestMapper.build(credentials));
    }
}
