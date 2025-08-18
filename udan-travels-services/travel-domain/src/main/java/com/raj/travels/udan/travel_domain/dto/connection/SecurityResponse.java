package com.raj.travels.udan.travel_domain.dto.connection;

import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import lombok.Data;

/**
 * Represents the response containing security details for a connection.
 * This class encapsulates the status, security token, token type, and expiration information.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Data
public class SecurityResponse {
    private String status;
    private String securityToken;
    private ConnectionType tokenType;
    private String expiresIn;
}
