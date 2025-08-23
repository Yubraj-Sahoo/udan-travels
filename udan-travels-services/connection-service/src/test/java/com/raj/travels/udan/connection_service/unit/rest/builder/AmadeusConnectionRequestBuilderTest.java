package com.raj.travels.udan.connection_service.unit.rest.builder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.rest.amadeus.builder.AmadeusConnectionRequestBuilder;
import com.raj.travels.udan.connection_service.rest.amadeus.builder.impl.AmadeusConnectionRequestBuilderImpl;
import com.raj.travels.udan.travel_domain.util.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AmadeusConnectionRequestBuilder.
 * This class contains unit tests to verify the functionality of the AmadeusConnectionRequestBuilder implementation.
 * It ensures that the build method correctly constructs an HttpEntity from the provided ConnectionCredentials.
 *
 * @version 1.0
 * @date 23-8-2025
 */
class AmadeusConnectionRequestBuilderTest {
    private final AmadeusConnectionRequestBuilder amadeusConnectionRequestBuilder = new AmadeusConnectionRequestBuilderImpl();

    /**
     * Test method for {@link AmadeusConnectionRequestBuilder#build(ConnectionCredentials)}.
     * This test verifies that the build method correctly constructs an HttpEntity
     * from the provided ConnectionCredentials.
     */
    @Test
    void testBuild() {
        ConnectionCredentials connectionCredentials = JsonUtils.readFromJsonFile("src/test/resources/json/connectionCredentials.json", new TypeReference<ConnectionCredentials>() {
        });

        assertNotNull(connectionCredentials);

        HttpEntity<String> httpEntity = amadeusConnectionRequestBuilder.build(connectionCredentials);

        assertNotNull(httpEntity);
        assertNotNull(httpEntity.getBody());
        assertFalse(httpEntity.getBody().isEmpty());
        assertNotNull(httpEntity.getHeaders());
        assertFalse(httpEntity.getHeaders().isEmpty());
    }

    /**
     * Test method for {@link AmadeusConnectionRequestBuilder#build(ConnectionCredentials)}.
     * This test verifies that the build method throws an IllegalArgumentException
     * when provided with incomplete ConnectionCredentials.
     */
    @Test
    void testBuildWithIncompleteCredentials() {
        ConnectionCredentials incompleteCredentials = new ConnectionCredentials();
        incompleteCredentials.setApiKey("testApiKey");
        assertThrows(IllegalArgumentException.class, () ->
                amadeusConnectionRequestBuilder.build(incompleteCredentials));
    }

    /**
     * Test method for {@link AmadeusConnectionRequestBuilder#build(ConnectionCredentials)}.
     * This test verifies that the build method throws an IllegalArgumentException
     * when provided with null ConnectionCredentials.
     */
    @Test
    void testBuildWithNullCredentials() {
        assertThrows(IllegalArgumentException.class, () ->
                amadeusConnectionRequestBuilder.build(null));
    }
}
