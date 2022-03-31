package com.planet.dashboard.repository.board;

import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.planet.dashboard.entity.QBoard.board;

public class BoardRepositoryImpl implements CustomBoardRepository {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<BoardDto> getBoardsSortedByCreatedAt(int pageNum, int pageSize) {
        List<Board> foundBoards = queryFactory.selectFrom(board)
                .orderBy(board.createdAt.desc())
                .offset(pageNum * pageSize)
                .limit(pageSize).fetch();
        return foundBoards.stream().map(BoardDto::new).collect(Collectors.toList());
    }

    @Override
    public Integer getNumOfBoards(){
        return queryFactory.selectFrom(board).fetch().size();
    }

}
