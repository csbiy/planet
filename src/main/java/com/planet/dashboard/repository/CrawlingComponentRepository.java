package com.planet.dashboard.repository;

import com.planet.dashboard.entity.CrawlingComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingComponentRepository extends JpaRepository<CrawlingComponent,Integer> {
}
