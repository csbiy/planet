package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Board extends BaseEntity {

    private static final int DEFAULT_VIEW_NUM = 0;
    private static final int DEFAULT_LIKE_NUM = 0;
    private static final int DEFAULT_DISLIKE_NUM = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_seq")
    private Long seq;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Integer viewNum;

    private Integer likeNum;

    private Integer dislikeNum;

    private String createdBy;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    private List<AttachedFile> files = new ArrayList<>();

    @Builder
    private Board(Long seq, String title, String content, Integer viewNum,
                 LocalDateTime createdAt , LocalDateTime updatedAt , LocalDateTime deletedAt ,
                 String deletedBy , String createdBy , Integer likeNum , Integer dislikeNum) {
        super(createdAt,updatedAt,deletedAt,deletedBy);
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.viewNum = viewNum;
        this.createdBy = createdBy;
        this.likeNum = likeNum;
        this.dislikeNum = dislikeNum;
    }

    public static Board createBoard(String createdBy , String title, String content){
        return builder()
                .viewNum(DEFAULT_VIEW_NUM)
                .likeNum(DEFAULT_LIKE_NUM)
                .dislikeNum(DEFAULT_DISLIKE_NUM)
                .createdBy(createdBy)
                .title(title)
                .content(content)
                .build();
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
    public void addFile(AttachedFile file){ this.files.add(file);}


}
