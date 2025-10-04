package com.raj.travels.udan.connection_service.integration.controllers;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.constants.MessageConstant;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConnectionControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    String connectionUrl = "/api/v1/connections/%s";

    @PreDestroy
    public void cleanUp() {
        restTemplate.getRestTemplate().getInterceptors().clear();
    }


    @Test
    void testFetchConnection() {
        ResponseEntity<ConnectionResponse> connectionResponseEntity = restTemplate.getForEntity(
                String.format(connectionUrl, Pseudocode.UDAN.getCode()),
                ConnectionResponse.class
        );

        assertNotNull(connectionResponseEntity);
        assertEquals(HttpStatus.OK, connectionResponseEntity.getStatusCode());

        ConnectionResponse connectionResponse = connectionResponseEntity.getBody();

        assertNotNull(connectionResponse);
        assertEquals(HttpStatus.OK, connectionResponse.getStatus());
        assertEquals(MessageConstant.CONNECTION_SUCCESS, connectionResponse.getMessage());

        assertNotNull(connectionResponse.getSecurityToken());
        assertNotNull(connectionResponse.getPseudocode());
        assertNotNull(connectionResponse.getExpiresIn());
    }
}
