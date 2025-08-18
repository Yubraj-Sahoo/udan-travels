package com.raj.travels.udan.travel_domain.domain.connection;

import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import lombok.Data;

/**
 * Represents the security details for a connection.
 * This class contains information about the type of connection, user credentials,
 * and security token details.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Data
public class AuthenticationDetails {
    private String type;
    private String userName;
    private ConnectionType connectionType;
    private String securityToken;
    private String applicationName;
    private String expiresIn;
    private String state;
}
