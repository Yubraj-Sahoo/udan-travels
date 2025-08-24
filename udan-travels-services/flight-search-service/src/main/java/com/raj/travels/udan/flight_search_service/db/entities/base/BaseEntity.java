package com.raj.travels.udan.flight_search_service.db.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Base entity class that contains common fields for all entities.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 9-8-2025
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int companyId = 1;

    @Column(nullable = false)
    private boolean active = true;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}