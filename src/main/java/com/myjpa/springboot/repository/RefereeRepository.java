package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefereeRepository extends JpaRepository<Referee,Integer> {
    Referee findByTeam_Id(String teamId);

}
