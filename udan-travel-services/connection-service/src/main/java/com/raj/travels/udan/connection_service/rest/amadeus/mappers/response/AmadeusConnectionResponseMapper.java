package com.raj.travels.udan.connection_service.rest.amadeus.mappers.response;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.dto.amadeus.AmadeusOAuth2TokenDto;

/**
 * Mapper interface for converting AmadeusOAuth2TokenDto to ConnectionResponse.
 */
public interface AmadeusConnectionResponseMapper {
    /**
     * Evaluates the given AmadeusOAuth2TokenDto and maps it to a ConnectionResponse.
     *
     * @param auth2Token            the AmadeusOAuth2TokenDto to be evaluated
     * @param connectionCredentials the connection credentials associated with the request
     * @return a ConnectionResponse containing the mapped data
     */
    ConnectionResponse evaluate(AmadeusOAuth2TokenDto auth2Token, ConnectionCredentials connectionCredentials);
}
