package com.planet.dashboard.repository;

import com.planet.dashboard.entity.CrawlingComponentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingComponentResultRepository extends JpaRepository<CrawlingComponentResult,Integer> {
}
