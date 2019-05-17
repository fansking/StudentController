package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefereeRepository extends JpaRepository<Referee,Integer> {
    Referee findByTeam_Id(String teamId);

    @Query(value = "select * from referees,teams where referees.team_id=teams.id and teams.account like ?1 and referees.name like ?2",nativeQuery = true)
    List<Referee> findByAccount(String account,String name);
}
