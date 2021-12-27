package com.planet.dashboard.repository;

import com.planet.dashboard.entity.User;
import com.planet.dashboard.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

}
