package com.planet.dashboard.controller.response.dto;

import com.planet.dashboard.entity.Board;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
public final class BoardDto {

    private final String title;

    private final String createdAt;

    private final String createdBy;

    private final Integer viewNum;

    private final Integer voteNum;

    private final Integer dislikeNum;

    public BoardDto(Board board) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm", Locale.KOREA);
        this.title = board.getTitle();
        this.createdAt = dateTimeFormatter.format(board.getCreatedAt());
        this.createdBy = board.getCreatedBy();
        this.viewNum = board.getViewNum();
        this.voteNum =  board.getLikeNum() - board.getDislikeNum();
        this.dislikeNum = board.getDislikeNum();
    }

}
