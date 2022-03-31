package com.planet.dashboard.repository.board;

import com.planet.dashboard.controller.response.dto.BoardDto;

import java.util.List;

interface CustomBoardRepository {

    List<BoardDto> getBoardsSortedByCreatedAt( int pageNum , int pageSize );

    Integer getNumOfBoards();

}
