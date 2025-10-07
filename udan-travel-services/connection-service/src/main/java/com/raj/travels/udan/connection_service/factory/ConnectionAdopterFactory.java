package com.raj.travels.udan.connection_service.factory;

import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.adopter.ConnectionAdopter;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;

public interface ConnectionAdopterFactory {
    ConnectionAdopter findAdopter(Pseudocode pseudocode);
}
