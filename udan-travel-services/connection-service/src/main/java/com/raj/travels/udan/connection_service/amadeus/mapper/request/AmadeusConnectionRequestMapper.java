package com.raj.travels.udan.connection_service.amadeus.mapper.request;

import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

/**
 * Mapper interface for building HTTP request entities for Amadeus connections.
 */
public interface AmadeusConnectionRequestMapper {

    /**
     * Builds an HTTP request entity using the provided connection credentials.
     *
     * @param connectionCredentials the credentials used to build the request
     * @return an HttpEntity containing the request data
     */
    HttpEntity<MultiValueMap<String, String>> build(ConnectionCredentials connectionCredentials);
}
