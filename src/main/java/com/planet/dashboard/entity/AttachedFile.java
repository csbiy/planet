package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class AttachedFile extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_seq")
    private Long id;

    private String fileName;

    private String fileSavePath;

    @Builder
    public AttachedFile(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, Long id, String fileName, String fileSavePath) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.id = id;
        this.fileName = fileName;
        this.fileSavePath = fileSavePath;
    }
}
