package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class CrawlingSite extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private String name;

    private String path;

    private String cssPath; //  study 공고 게시글



    @Builder
    public CrawlingSite(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, Integer id, String name, String path, String cssPath ) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.id = id;
        this.name = name;
        this.path = path;
        this.cssPath = cssPath;
    }
}

