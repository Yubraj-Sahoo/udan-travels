package com.raj.travels.udan.connection_service.controller;

import com.raj.travels.udan.connection_service.service.ConnectionService;
import com.raj.travels.udan.travel_domain.dto.connection.AuthenticationResponse;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing connections to various GDS services.
 * This controller provides endpoints to retrieve authentication tokens for different connection types.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-8-2025
 */
@RestController
@RequestMapping("/api/v1/connections")
@RequiredArgsConstructor
@Slf4j
public class ConnectionController {
    private final ConnectionService connectionService;

    @GetMapping("/amadeus/{connection}")
    public ResponseEntity<AuthenticationResponse> getAmadeusAuthenticationToken(
            @PathVariable("connection") String connection) {
        log.info("Received request to get Amadeus authentication token for connection: {}",connection);
        ConnectionType connectionType = ConnectionType.fromServiceId(connection);
        AuthenticationResponse response = connectionService.getConnection(connectionType);
        return ResponseEntity.ok(response);
    }
}
