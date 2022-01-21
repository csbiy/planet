package com.planet.dashboard.service;

import com.planet.dashboard.controller.response.dto.BoardDetailDto;
import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.repository.board.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    void init(){}

    @ParameterizedTest
    @CsvSource({"admin,title,content"})
    void findById(String createdBy , String title , String cnt ) {
        Board board = Board.createBoard(createdBy, title, cnt);
        boardRepository.save(board);
        BoardDetailDto boardDetailDto = boardService.findById(board.getSeq());
        assertThat(boardDetailDto.getCreatedBy()).isEqualTo(createdBy);
        assertThat(boardDetailDto.getTitle()).isEqualTo(title);
        assertThat(boardDetailDto.getContent()).isEqualTo(cnt);

    }
}