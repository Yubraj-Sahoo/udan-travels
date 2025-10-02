package com.raj.travels.udan.connection_service.rest.amadeus.mappers.response.impl;

import com.raj.travels.commons.dto.ConnectionResponse;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.dto.amadeus.AmadeusOAuth2TokenDto;
import com.raj.travels.udan.connection_service.exceptions.ConnectionFailedException;
import com.raj.travels.udan.connection_service.rest.amadeus.mappers.response.AmadeusConnectionResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class AmadeusConnectionResponseMapperImpl implements AmadeusConnectionResponseMapper {
    /**
     * Evaluates the given AmadeusOAuth2TokenDto and maps it to a ConnectionResponse.
     *
     * @param auth2Token            the AmadeusOAuth2TokenDto to be evaluated
     * @param connectionCredentials the connection credentials associated with the request
     * @return a ConnectionResponse containing the mapped data
     */
    @Override
    public ConnectionResponse evaluate(AmadeusOAuth2TokenDto auth2Token, ConnectionCredentials connectionCredentials) {
        if (validateTokenRes(auth2Token)) {
            throw new ConnectionFailedException("Failed to fetch Amadeus connection: auth2Token is null");
        }
        return ConnectionResponse.builder()
                .pseudocode(connectionCredentials.getPseudocode())
                .securityToken(auth2Token.getAccessToken())
                .expiresIn((long) auth2Token.getExpiresIn())
                .build();
    }

    /**
     * Validates the AmadeusOAuth2TokenDto to ensure it contains valid data.
     *
     * @param auth2Token the AmadeusOAuth2TokenDto to be validated
     * @return true if the token is invalid, false otherwise
     */
    private boolean validateTokenRes(AmadeusOAuth2TokenDto auth2Token) {
        return auth2Token == null || auth2Token.getAccessToken() == null || auth2Token.getAccessToken().isEmpty()
                || auth2Token.getTokenType() == null || auth2Token.getTokenType().isEmpty()
                || auth2Token.getExpiresIn() <= 0;
    }
}
