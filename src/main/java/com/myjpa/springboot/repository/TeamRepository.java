package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,String> {
    Team findTeamByName(String name);
    Team findTeamByAccount(String account);
    List<Team> findAll();

    @Query(value = "select * from teams where account like ?1 and pass_word like ?2",nativeQuery = true)
    List<Team> findByAccountAndPassWord(String account,String passWord);
}
