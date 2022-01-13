package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    Integer viewNum;

    String deletedBy;


}
