package com.raj.travels.udan.connection_service.dto.amadeus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing the Amadeus OAuth2 token response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmadeusOAuth2TokenDto {

    @JsonProperty("type")
    private String type;

    @JsonProperty("username")
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
    private int expiresIn;

    @JsonProperty("state")
    private String state;

    @JsonProperty("scope")
    private String scope;
}
