package com.raj.travels.udan.connection_service.mapper;

import com.raj.travels.udan.travel_domain.domain.connection.AuthenticationDetails;
import com.raj.travels.udan.travel_domain.dto.connection.AuthenticationResponse;
import org.springframework.http.HttpStatus;

public class AuthenticationMapper {
    private AuthenticationMapper() {
        // private constructor to prevent instantiation
    }

    /**
     * Maps ConnectionCredentials to AuthenticationResponse.
     *
     * @param connectionCredentials the connection credentials to map
     * @return the mapped authentication response
     */
    public static AuthenticationResponse mapToAuthenticationResponse(AuthenticationDetails connectionCredentials) {
        return AuthenticationResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .securityToken(connectionCredentials.getSecurityToken())
                .tokenType(connectionCredentials.getConnectionType())
                .expiresIn(String.valueOf(connectionCredentials.getExpiresIn()))
                .build();
    }
}
