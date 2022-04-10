package com.planet.dashboard.controller.response.dto;

import com.planet.dashboard.entity.Board;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
public class BoardDetailDto {

    private final String title;

    private final String content;

    private final String createdAt;

    private final String createdBy;

    private final Integer viewNum;

    private final Integer likeNum;

    private final Integer dislikeNum;

    private final List<CommentDto> comments;


    public BoardDetailDto(Board board) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm", Locale.KOREA);
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = dateTimeFormatter.format(board.getCreatedAt());
        this.createdBy = board.getCreatedBy();
        this.viewNum = board.getViewNum();
        this.likeNum = board.getLikeNum();
        this.dislikeNum = board.getDislikeNum();
        this.comments = board.getComments().stream().map(CommentDto::new).collect(Collectors.toList());

    }
}
