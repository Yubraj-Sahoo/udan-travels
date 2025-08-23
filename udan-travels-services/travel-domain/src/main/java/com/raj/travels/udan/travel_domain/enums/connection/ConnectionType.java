package com.raj.travels.udan.travel_domain.enums.connection;

import lombok.Getter;

/**
 * Enum representing different types of connections available in the system.
 * Each connection type is associated with a GDS type and a service ID.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 2-7-2025
 */
@Getter
public enum ConnectionType {
    Y_FLIGHT(GdsType.AMADEUS, "yflight"),
    UDAN_TRAVELS(GdsType.AMADEUS, "udantravels");

    private final GdsType gdsType;
    private final String serviceId;

    ConnectionType(GdsType gdsType, String serviceId) {
        this.gdsType = gdsType;
        this.serviceId = serviceId;
    }

    public static ConnectionType fromServiceId(String serviceId) {
        for (ConnectionType service : values()) {
            if (service.getServiceId().equalsIgnoreCase(serviceId)) {
                return service;
            }
        }
        throw new IllegalArgumentException("No service found with serviceId: " + serviceId);
    }
}
