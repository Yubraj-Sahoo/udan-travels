package com.raj.travel.udan.services.connection.db.entity;

import com.raj.travel.udan.services.connection.converter.Base64AttributeConverter;
import com.raj.travel.udan.services.connection.db.entity.base.BaseEntity;
import com.raj.travel.udan.services.connection.enums.GdsType;
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

    private String serviceId;

    @Enumerated(EnumType.STRING)
    private GdsType gdsType;

    private String serviceName;

    @Convert(converter = Base64AttributeConverter.class)
    private String apiKey;

    @Convert(converter = Base64AttributeConverter.class)
    private String apiSecret;
}
