package com.raj.travels.udan.connection_service.adopter;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.commons.enums.GdsType;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;

public interface ConnectionAdopter {
    GdsType getGdsType();
    ConnectionResponse fetchConnection(ConnectionCredentials connectionCredentials);
}
