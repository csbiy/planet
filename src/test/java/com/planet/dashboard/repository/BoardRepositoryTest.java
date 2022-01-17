package com.planet.dashboard.repository;

import com.planet.dashboard.entity.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.PropertyResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
     BoardRepository boardRepository;

    @Test
    void findAllBoardOrderByCreatedAtAsc() {
        boardRepository.save(Board.builder().content("contentA").title("titleA").build());
        boardRepository.save(Board.builder().content("contentB").title("titleB").build());
        Page<Board> result = boardRepository.findAllByOrderByCreatedAtAsc( PageRequest.of(0,20, Sort.by("createdAt")));
        assertThat(result.getTotalPages()).isEqualTo(1);
        Board foundBoardA = result.getContent().get(0);
        assertThat(foundBoardA.getTitle()).isEqualTo("titleA");
        assertThat(foundBoardA.getContent()).isEqualTo("contentA");
        Board foundBoardB = result.getContent().get(1);
        assertThat(foundBoardB.getTitle()).isEqualTo("titleB");
        assertThat(foundBoardB.getContent()).isEqualTo("contentB");
    }

}