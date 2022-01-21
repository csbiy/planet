package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "comment_seq")
    private Long id;

    private String content;

    private Integer likeNum;

    private Integer dislikeNum;

    @ManyToOne
    private User user;

    @Builder
    public Comment(LocalDateTime createdAt , LocalDateTime updatedAt , LocalDateTime deletedAt ,
                   String deletedBy , Long id, String content, Integer likeNum , Integer dislikeNum){
            super(createdAt,updatedAt,deletedAt,deletedBy);
            this.id = id;
            this.content = content;
            this.likeNum = likeNum;
            this.dislikeNum = dislikeNum;
    }

    public static Comment createComment(String content , User user) {
        return new Comment(content,user);
    }

    private Comment(String content,User user){
        this.content = content;
        this.user = user;
    }


}
