package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class CrawlingComponent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cssClass;

    private String tagPath;

    private String name;

    private String value;


    @Builder
    public CrawlingComponent(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, Integer id, String cssClass, String tagPath, String name, String value) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.id = id;
        this.cssClass = cssClass;
        this.tagPath = tagPath;
        this.name = name;
        this.value = value;
    }

}
