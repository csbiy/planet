package com.planet.dashboard.repository.board;

import com.planet.dashboard.controller.response.dto.BoardDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.planet.dashboard.entity.QBoard.board;


public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    public BoardRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardDto> findAllByOrderByCreatedAtAsc(Pageable pageable) {
        List<BoardDto> boardList = jpaQueryFactory
                .select(Projections.constructor(BoardDto.class, board))
                .from(board)
                .orderBy(board.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int totalCount = jpaQueryFactory.selectFrom(board).fetch().size();
        return new PageImpl<>(boardList,pageable,totalCount);
    }



}
