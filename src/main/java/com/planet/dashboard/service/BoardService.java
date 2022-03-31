package com.planet.dashboard.service;

import com.planet.dashboard.controller.request.dto.BoardForm;
import com.planet.dashboard.controller.response.dto.BoardDetailDto;
import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.AttachedFile;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private static final int PAGE_SIZE = 20;
    private final BoardRepository boardRepository;

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
    public Board createBoardWithFile(BoardForm boardForm , String nickName , List<AttachedFile> attachedFiles) {
        Board board = Board.createBoard(nickName, boardForm.getTitle(), boardForm.getContent());
        attachedFiles.stream().forEach(board::addFile);
        return boardRepository.save(board);
    }


    public List<BoardDto> getBoardsSortedByrCreatedAt(int pageNum) {
        return boardRepository.getBoardsSortedByCreatedAt(pageNum, PAGE_SIZE);
    }

    public Integer getNumOfBoards(){
        Integer size = boardRepository.getNumOfBoards();
        if(size%PAGE_SIZE != 0){
            return size/PAGE_SIZE+1;
        }
        return size == 0 ? 1 : size/PAGE_SIZE;
    }
}
