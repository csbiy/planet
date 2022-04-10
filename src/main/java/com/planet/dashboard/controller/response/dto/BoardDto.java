package com.planet.dashboard.controller.response.dto;

import com.planet.dashboard.entity.Board;
import lombok.Getter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@ToString
public final class BoardDto {


    private final Long seq;
    private final String title;

    private final String createdAt;

    private final String createdBy;

    private final Integer viewNum;

    private final Integer voteNum;


    public BoardDto(Board board) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm", Locale.KOREA);
        this.seq = board.getSeq();
        this.title = board.getTitle();
        this.createdAt = dateTimeFormatter.format(board.getCreatedAt());
        this.createdBy = board.getCreatedBy();
        this.viewNum = board.getViewNum();
        this.voteNum =  board.getLikeNum() - board.getDislikeNum();
    }

}
