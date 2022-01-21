package com.planet.dashboard.controller.response.dto;

import com.planet.dashboard.entity.Comment;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
public class CommentDto {
    private final String content;

    private final Integer likeNum;

    private final Integer dislikeNum;

    private final String nickName;
    private final String createdAt;

    public CommentDto(Comment comment){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm", Locale.KOREA);
        this.createdAt = dateTimeFormatter.format(comment.getCreatedAt());
        this.content = comment.getContent();
        this.likeNum = comment.getLikeNum();
        this.dislikeNum  = comment.getDislikeNum();
        this.nickName = comment.getUser().getNickName();
    }
}
