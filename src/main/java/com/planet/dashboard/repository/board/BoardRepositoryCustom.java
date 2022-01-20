package com.planet.dashboard.repository.board;

import com.planet.dashboard.controller.response.dto.BoardDto;
import com.planet.dashboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BoardDto> findAllByOrderByCreatedAtAsc(Pageable pageable);
}
