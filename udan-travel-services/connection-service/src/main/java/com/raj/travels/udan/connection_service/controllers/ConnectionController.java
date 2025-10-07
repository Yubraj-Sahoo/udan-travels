package com.raj.travels.udan.connection_service.controllers;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.service.ConnectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller for managing connections.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @since 2025-10-02
 */
@RestController
@RequestMapping(value = "/api/v1/connections")
@AllArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    /**
     * Fetches connection details based on the provided pseudocode.
     *
     * @param pseudocode the pseudocode of the connection
     * @return ResponseEntity containing ConnectionResponse
     */
    @GetMapping(value = "/{pseudoCode}")
    public ResponseEntity<ConnectionResponse> fetchConnection(@PathVariable(value = "pseudoCode") String pseudocode) {
        Pseudocode code = Optional.ofNullable(Pseudocode.fromCode(pseudocode.toUpperCase()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid pseudocode: " + pseudocode));

        return ResponseEntity.ok(connectionService.fetchConnection(code));
    }
}
