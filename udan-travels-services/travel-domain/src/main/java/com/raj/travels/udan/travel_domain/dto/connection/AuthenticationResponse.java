package com.raj.travels.udan.travel_domain.dto.connection;

import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response containing security details for a connection.
 * This class encapsulates the status, security token, token type, and expiration information.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String status;
    private String errorMessage;
    private String securityToken;
    private ConnectionType tokenType;
    private String expiresIn;
}
