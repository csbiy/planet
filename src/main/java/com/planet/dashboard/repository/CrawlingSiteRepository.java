package com.planet.dashboard.repository;

import com.planet.dashboard.entity.CrawlingSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingSiteRepository extends JpaRepository<CrawlingSite,Integer> {
}
