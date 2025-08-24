package com.raj.travels.udan.flight_search_service.rest;

import com.raj.travels.udan.travel_domain.dto.connection.AuthenticationResponse;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;

/**
 * Service interface for fetching authentication details.
 */
public interface ConnectionService {
    /**
     * Fetches authentication details.
     *
     * @return AuthenticationResponse containing authentication information.
     */
    String fetchAuthenticationToken(ConnectionType connectionType);
}
