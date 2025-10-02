package com.raj.travels.udan.connection_service.db.repositories;

import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing ConnectionCredentials entities.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @since 2025-10-02
 */
@Repository
public interface ConnectionCredentialsRepository extends JpaRepository<ConnectionCredentials, Long> {

    /**
     * Finds active ConnectionCredentials by pseudocode.
     *
     * @param pseudocode the pseudocode to search for
     * @return the ConnectionCredentials entity if found, otherwise null
     */
    @Query("SELECT c FROM ConnectionCredentials c WHERE c.pseudocode = :pseudocode AND c.active = true")
    ConnectionCredentials findByPseudocode(@Param("pseudocode") Pseudocode pseudocode);
}
