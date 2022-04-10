package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class CrawlingComponentResult extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = Integer.MAX_VALUE)
    private String value;

    private String boardId;

    @ManyToOne
    private CrawlingComponent crawlingComponent;

    @Builder
    public CrawlingComponentResult(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, Integer id, String value, String boardId, CrawlingComponent crawlingComponent) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.id = id;
        this.value = value;
        this.boardId = boardId;
        this.crawlingComponent = crawlingComponent;
    }
}
