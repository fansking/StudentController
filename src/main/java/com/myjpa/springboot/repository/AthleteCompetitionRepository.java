package com.myjpa.springboot.repository;


import com.myjpa.springboot.entity.AthleteCompetition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteCompetitionRepository extends JpaRepository<AthleteCompetition,String> {
    @Override
    AthleteCompetition save(AthleteCompetition entity);
    List<AthleteCompetition> findByAthlete_IdAndCompetition_Id(String athleteId,String competitionId);
    List<AthleteCompetition> findByAthlete_Id(String athleteId);
    List<AthleteCompetition> findByCompetition_Id(String competitionId);
}
