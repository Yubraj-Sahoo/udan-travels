package com.raj.travels.udan.connection_service.adopter.impl;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.GdsType;
import com.raj.travels.udan.connection_service.adopter.ConnectionAdopter;
import com.raj.travels.udan.connection_service.amadeus.client.AmadeusConnectionClient;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmadeusConnectionAdopter implements ConnectionAdopter {
    private static final GdsType GDS_TYPE = GdsType.AMADEUS;

    private final AmadeusConnectionClient amadeusConnectionClient;

    @Override
    public GdsType getGdsType() {
        return GDS_TYPE;
    }

    @Override
    public ConnectionResponse fetchConnection(ConnectionCredentials connectionCredentials) {
        return amadeusConnectionClient.fetchConnection(connectionCredentials);
    }
}
