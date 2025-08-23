package com.raj.travels.udan.travel_domain.enums.connection;

import lombok.Getter;

/**
 * Enum representing different types of Global Distribution Systems (GDS).
 * Currently, only Amadeus is supported.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Getter
public enum GdsType {

    AMADEUS("Amadeus");

    private final String name;

    GdsType(String name) {
        this.name = name;
    }

}
