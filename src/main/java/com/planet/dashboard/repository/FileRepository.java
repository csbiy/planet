package com.planet.dashboard.repository;

import com.planet.dashboard.entity.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<AttachedFile,Long> {
}
