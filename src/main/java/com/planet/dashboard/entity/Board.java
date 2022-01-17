package com.planet.dashboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    Integer viewNum;

    String createdBy;

    @Builder
    public Board(Long seq, String title, String content, Integer viewNum, LocalDateTime dateTime , LocalDateTime updatedAt , LocalDateTime deletedAt ,String deletedBy , String createdBy) {
        super(dateTime,updatedAt,deletedAt,deletedBy);
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.viewNum = viewNum;
        this.createdBy = createdBy;
    }
}
