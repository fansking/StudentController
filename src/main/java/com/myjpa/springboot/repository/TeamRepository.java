package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,String> {

}
