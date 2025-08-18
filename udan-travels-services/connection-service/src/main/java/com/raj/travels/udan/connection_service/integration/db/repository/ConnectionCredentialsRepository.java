package com.raj.travels.udan.connection_service.integration.db.repository;

import com.raj.travels.udan.connection_service.db.entity.ConnectionCredentials;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import com.raj.travels.udan.travel_domain.enums.connection.GdsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConnectionCredentialsRepository extends JpaRepository<ConnectionCredentials, Long> {

    /**
     * Finds a ConnectionCredentials entity by its GDS type and connection type.
     *
     * @param gdsType        the GDS type of the connection
     * @param connectionType the connection type
     * @return the ConnectionCredentials entity if found, otherwise null
     */
    @Query("SELECT c FROM ConnectionCredentials c WHERE c.gdsType = :gdsType AND c.connectionType = :connectionType AND c.active = true")
    ConnectionCredentials findByGdsTypeAndConnectionType(GdsType gdsType, ConnectionType connectionType);
}
