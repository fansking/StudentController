package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Team;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,String> {
    Team findTeamByName(String name);
    Team findTeamByAccount(String account);
    List<Team> findAll();
}
