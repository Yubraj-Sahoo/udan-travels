package com.raj.travels.udan.flight_search_service.integration.db.repository;

import com.raj.travels.udan.flight_search_service.db.entities.AirSearchServiceCallConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirSearchServiceCallConfigurationRepositoryTest {
    @Autowired
    private AirSearchServiceCallConfigurationRepository serviceCallConfigurationRepository;

    @Test
    @Disabled("Disabled due to using db")
    void testFindByActive(){
        List<AirSearchServiceCallConfiguration> configurations = serviceCallConfigurationRepository.findByActive(true);
        assertNotNull(configurations);
        assertFalse(configurations.isEmpty());
    }
}
