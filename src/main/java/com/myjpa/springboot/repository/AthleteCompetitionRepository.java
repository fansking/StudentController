package com.myjpa.springboot.repository;


import com.myjpa.springboot.entity.AthleteCompetition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteCompetitionRepository extends JpaRepository<AthleteCompetition,Integer> {
    @Override
    AthleteCompetition save(AthleteCompetition entity);
    List<AthleteCompetition> findByAthlete_IdAndCompetition_Id(Integer athleteId,Integer competitionId);
    List<AthleteCompetition> findByAthlete_Id(Integer athleteId);
    List<AthleteCompetition> findByCompetition_IdOrderByScoreDesc(Integer competitionId);
}
