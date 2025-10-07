package com.raj.travels.udan.connection_service.db.entities;

import com.raj.travels.commons.converters.EncryptionConverter;
import com.raj.travels.commons.db.entities.base.BaseEntity;
import com.raj.travels.commons.enums.Pseudocode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity representing connection credentials for external services.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @since 2025-10-02
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "connection_credentials")
public class ConnectionCredentials extends BaseEntity {
    @Column(nullable = false)
    @Convert(converter = EncryptionConverter.class)
    private String apiKey;
    @Column(nullable = false)
    @Convert(converter = EncryptionConverter.class)
    private String apiSecret;
    private String serviceName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Pseudocode pseudocode;
}
