package com.raj.travels.udan.connection_service.db.entities;

import com.raj.travels.udan.connection_service.db.entities.base.BaseEntity;
import com.raj.travels.udan.connection_service.db.converter.Base64AttributeConverter;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import com.raj.travels.udan.travel_domain.enums.connection.GdsType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Represents a connection service for Amadeus API.
 * This entity is used to store the service details required for connecting to Amadeus.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Entity
@Getter
@Setter
@Table(name = "connection_credentials")
public class ConnectionCredentials extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ConnectionType connectionType;

    @Enumerated(EnumType.STRING)
    private GdsType gdsType;

    @Column(nullable = false)
    private String grantType;

    @Convert(converter = Base64AttributeConverter.class)
    private String apiKey;

    @Convert(converter = Base64AttributeConverter.class)
    private String apiSecret;
}
