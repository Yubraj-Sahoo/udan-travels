package com.raj.travels.udan.connection_service.rest.amadeus.service.impl;

import com.raj.travels.commons.dto.ConnectionResponse;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.dto.amadeus.AmadeusOAuth2TokenDto;
import com.raj.travels.udan.connection_service.rest.amadeus.mappers.request.AmadeusConnectionRequestMapper;
import com.raj.travels.udan.connection_service.rest.amadeus.mappers.response.AmadeusConnectionResponseMapper;
import com.raj.travels.udan.connection_service.rest.amadeus.service.AmadeusConnectionRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AmadeusConnectionRestServiceImpl implements AmadeusConnectionRestService {
    private final AmadeusConnectionRequestMapper amadeusConnectionRequestMapper;
    private final AmadeusConnectionResponseMapper amadeusConnectionResponseMapper;
    private final RestTemplate restTemplate;

    @Value("${amadeus.auth.url}")
    private String amadeusAuthUrl;

    /**
     * Fetches a connection using the provided connection credentials.
     *
     * @param connectionCredentials the credentials used to fetch the connection
     * @return a ConnectionResponse containing the connection details
     */
    @Override
    public ConnectionResponse fetchConnection(ConnectionCredentials connectionCredentials) {
        HttpEntity<MultiValueMap<String, String>> request = amadeusConnectionRequestMapper.build(connectionCredentials);

        AmadeusOAuth2TokenDto auth2Token = restTemplate.postForObject(
                amadeusAuthUrl,
                request,
                AmadeusOAuth2TokenDto.class
        );

        return amadeusConnectionResponseMapper.evaluate(auth2Token, connectionCredentials);
    }
}
