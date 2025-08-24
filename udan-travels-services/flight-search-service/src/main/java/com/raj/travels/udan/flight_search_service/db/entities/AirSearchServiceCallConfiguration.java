package com.raj.travels.udan.flight_search_service.db.entities;

import com.raj.travels.udan.flight_search_service.db.entities.base.BaseEntity;
import com.raj.travels.udan.travel_domain.enums.air.TripType;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import com.raj.travels.udan.travel_domain.enums.connection.GdsType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Entity representing the configuration for making service calls to air search providers.
 * This entity includes details such as trip type, origin, destination, and connection type.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 23-8-2025
 */
@Entity
@Table(name = "air_search_service_call_configuration")
@Data
@EqualsAndHashCode(callSuper = true)
public class AirSearchServiceCallConfiguration extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    //    if null then consider as all trip types
    @Enumerated(EnumType.STRING)
    private TripType tripType;

    private String origin;

    private String destination;

    private String originCountryCode;

    private String destinationCountryCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ConnectionType connectionType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GdsType gdsType;
}
