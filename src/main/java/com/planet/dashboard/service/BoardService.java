package com.planet.dashboard.service;

import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private static final int PAGE_SIZE = 20;
    private final BoardRepository boardRepository;

    public Page<BoardDto> getBoardsSortedByrCreatedAt(Integer pageNum){
        return boardRepository.findAllByOrderByCreatedAtAsc(PageRequest.of(pageNum, PAGE_SIZE));
    }
}
