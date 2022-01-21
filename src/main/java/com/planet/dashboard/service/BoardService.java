package com.planet.dashboard.service;

import com.planet.dashboard.controller.request.dto.BoardForm;
import com.planet.dashboard.controller.response.dto.BoardDetailDto;
import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {


    private static final int PAGE_SIZE = 20;


    private final BoardRepository boardRepository;



    public Page<BoardDto> getBoardsSortedByrCreatedAt(Integer pageNum){
        return boardRepository.findAllByOrderByCreatedAtAsc(PageRequest.of(pageNum, PAGE_SIZE));
    }

    @Transactional
    public BoardDetailDto findById(Long id){
        Optional<Board> foundBoard = boardRepository.findById(id);
        if(foundBoard.isPresent()){
           return new BoardDetailDto(foundBoard.get());
        }
        return null;
    }

    @Transactional
    public Board createBoard(BoardForm boardForm , String nickName) {
        return boardRepository.save( Board.createBoard(nickName, boardForm.getTitle(), boardForm.getContent()));
    }
}
