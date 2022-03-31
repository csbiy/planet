package com.planet.dashboard.repository.board;

import com.planet.dashboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> , CustomBoardRepository {


}
