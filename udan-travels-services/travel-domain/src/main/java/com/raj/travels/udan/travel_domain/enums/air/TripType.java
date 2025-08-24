package com.raj.travels.udan.travel_domain.enums.air;

import lombok.Getter;

/**
 * Enum representing different types of trips.
 * Each trip type has a corresponding display name for better readability.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 2025-08-23
 */
@Getter
public enum TripType {
    OW("One Way"),
    RT("Round Trip"),
    MT("Multi City");

    private final String displayName;

    TripType(String displayName) {
        this.displayName = displayName;
    }
}
