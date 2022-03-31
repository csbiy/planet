package com.planet.dashboard.service;

import com.planet.dashboard.controller.response.dto.BoardDetailDto;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.repository.board.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


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
    @ParameterizedTest
    @DisplayName("현재 게시판 개수의 올림값만큼을 표시해주어야 합니다. ex-> 42개 게시글이 있고 pageSize = 20 인경우, 3개의 button 이 필요합니다.  ")
    @CsvSource({"40,2","41,3"})
    void getNumOfBoards(int size , int expectedButtonNum ) {

        for(int i = 0; i <size ; i++){
            boardRepository.save(Board.createBoard("kcs","hello", UUID.randomUUID().toString().substring(0,20)));
        }
        Integer numOfBoards = boardService.getNumOfBoards();
        Assertions.assertThat(numOfBoards).isEqualTo(expectedButtonNum);
    }
}