package com.raj.travels.udan.flight_search_service.integration.db.repository;

import com.raj.travels.udan.flight_search_service.db.entities.AirSearchServiceCallConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing AirSearchServiceCallConfiguration entities.
 * This interface extends JpaRepository to provide CRUD operations and custom query methods.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-08-2025
 */
public interface AirSearchServiceCallConfigurationRepository extends JpaRepository<AirSearchServiceCallConfiguration, Long> {
    /**
     * Find configurations by active.
     *
     * @param active isActive
     * @return the list of active configurations.
     */
    List<AirSearchServiceCallConfiguration> findByActive(boolean active);
}
