package com.myjpa.springboot.repository;


import com.myjpa.springboot.entity.AthleteCompetition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteCompetitionRepository extends JpaRepository<AthleteCompetition,Integer> {
}
