package com.planet.dashboard.repository;

import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.repository.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    void findAllBoardOrderByCreatedAtAsc() {
        boardRepository.save(Board.builder().content("contentA").title("titleA").build());
        boardRepository.save(Board.builder().content("contentB").title("titleB").build());
        Page<BoardDto> result = boardRepository.findAllByOrderByCreatedAtAsc( PageRequest.of(0,20, Sort.by("createdAt")));
        assertThat(result.getTotalPages()).isEqualTo(1);
        BoardDto foundBoardA = result.getContent().get(0);
        assertThat(foundBoardA.getTitle()).isEqualTo("titleA");
        BoardDto foundBoardB = result.getContent().get(1);
        assertThat(foundBoardB.getTitle()).isEqualTo("titleB");
    }

}