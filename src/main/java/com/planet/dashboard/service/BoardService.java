package com.planet.dashboard.service;

import com.planet.dashboard.auth.PrincipalDetails;
import com.planet.dashboard.controller.request.dto.BoardForm;
import com.planet.dashboard.controller.response.dto.BoardDetailDto;
import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.Board;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import com.planet.dashboard.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private static final int PAGE_SIZE = 20;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardDetailDto readBoard(Long id){
        Optional<Board> foundBoard = boardRepository.findById(id);
        if(foundBoard.isPresent()){
            foundBoard.get().addViewNum();
            return new BoardDetailDto(foundBoard.get());
        }
        return null;
    }

    public Board createBoard(BoardForm boardForm , String nickName) {
        return boardRepository.save( Board.createBoard(nickName, boardForm.getTitle(), boardForm.getContent()));
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

    public String findNickName(Authentication authentication) {
        String nickName = null;
        if(authentication instanceof UserDetails){
            PrincipalDetails userDetails = (PrincipalDetails) authentication;
            nickName = userDetails.getUser().getNickName();
        }else if(authentication instanceof UsernamePasswordAuthenticationToken){
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
            Optional<User> foundUser = userRepository.findByEmail(token.getName());
            nickName = foundUser.get().getNickName();
        }
        return nickName;
    }

}
