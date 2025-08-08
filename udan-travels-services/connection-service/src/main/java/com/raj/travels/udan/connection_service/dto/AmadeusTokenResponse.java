package com.raj.travels.udan.connection_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents the response received from the Amadeus token service.
 * This class is used to map the JSON response from the Amadeus API to a Java object.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 2-7-2025
 */
@Data
public class AmadeusTokenResponse {
    private String type;

    private String username;

    @JsonProperty("application_name")
    private String applicationName;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

    private String state;

    private String scope;
}
