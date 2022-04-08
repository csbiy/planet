package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CrawlingSite extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private String name;

    private String path;

    @OneToMany(fetch = FetchType.EAGER )
    private List<CrawlingComponent> components = new ArrayList();


    public void addComponent(CrawlingComponent crawlingComponent){
        this.components.add(crawlingComponent);
    }

    @Builder
    public CrawlingSite(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, Integer id, String name, String path) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.id = id;
        this.name = name;
        this.path = path;
    }

}

