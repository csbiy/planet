package com.planet.dashboard.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class AttachedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_seq")
    private Long id;

    private String fileName;

    private String fileSavePath;


}
