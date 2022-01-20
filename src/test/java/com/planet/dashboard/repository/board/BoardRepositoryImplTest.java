package com.planet.dashboard.repository.board;

import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryImplTest {

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    void init(){
        boardRepository.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({"0,10,40","1,20,40","5,10,40"})
    void findAllByOrderByCreatedAtAsc(int page , int size , int tupleNum) {
        IntStream.range(0,tupleNum).forEach((num)->boardRepository.save(Board.createBoard("admin","test","testContent")));
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BoardDto> result = boardRepository.findAllByOrderByCreatedAtAsc(pageRequest);

        if(!(tupleNum < size*page)){
            assertThat(result.getContent().size()).isEqualTo(size);
        }else{
            assertThat(result.getContent().size()).isEqualTo(0);
        }

        assertThat(result.getTotalPages()).isEqualTo((int) Math.ceil(tupleNum/size));
    }
}