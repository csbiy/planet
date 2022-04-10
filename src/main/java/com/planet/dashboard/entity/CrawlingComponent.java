package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class CrawlingComponent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cssPath;

    private String name;

    @ManyToOne
    @JoinColumn(name = "crawling_site_id")
    private CrawlingSite crawlingSite;


    @Builder
    public CrawlingComponent(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, Integer id, String cssPath, String name, CrawlingSite site) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.id = id;
        this.cssPath = cssPath;
        this.name = name;
        this.crawlingSite = site;
    }

}
