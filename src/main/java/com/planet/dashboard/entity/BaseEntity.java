package com.planet.dashboard.entity;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
