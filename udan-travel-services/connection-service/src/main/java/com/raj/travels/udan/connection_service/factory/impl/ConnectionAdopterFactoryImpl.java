package com.raj.travels.udan.connection_service.factory.impl;

import com.raj.travels.commons.enums.GdsType;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.adopter.ConnectionAdopter;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.factory.ConnectionAdopterFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionAdopterFactoryImpl implements ConnectionAdopterFactory {

    private final List<ConnectionAdopter> connectionAdopters;
    private final Map<GdsType, ConnectionAdopter> connectionAdopterMap = new EnumMap<>(GdsType.class);

    /**
     * Initializes the adopter map after all beans are created.
     * Ensures each GDS type has exactly one adapter implementation.
     */
    @PostConstruct
    void init() {
        for (ConnectionAdopter adopter : connectionAdopters) {
            GdsType gdsType = adopter.getGdsType();
            if (connectionAdopterMap.containsKey(gdsType)) {
                log.warn("Duplicate ConnectionAdopter detected for GDS type: {}.", gdsType);
                continue;
            }
            connectionAdopterMap.put(gdsType, adopter);
        }
        log.info("Initialized ConnectionAdopterFactory with adopters: {}", connectionAdopterMap.keySet());
    }

    /**
     * Finds the appropriate connection adopter based on the given connection credentials.
     *
     * @param pseudocode the pseudocode containing the GDS type information
     * @return the appropriate {@link ConnectionAdopter}
     * @throws IllegalArgumentException if no matching adopter is found
     */
    @Override
    public ConnectionAdopter findAdopter(Pseudocode pseudocode) {
        GdsType gdsType = Optional.ofNullable(pseudocode.getGdsType())
                .orElseThrow(() -> new IllegalArgumentException("Pseudocode or GDS type cannot be null"));

        ConnectionAdopter adopter = connectionAdopterMap.get(gdsType);
        if (adopter == null) {
            throw new IllegalArgumentException("No ConnectionAdopter found for GDS type: " + gdsType);
        }

        log.debug("Fetched adopter [{}] for GDS type [{}]", adopter.getClass().getSimpleName(), gdsType);
        return adopter;
    }
}
