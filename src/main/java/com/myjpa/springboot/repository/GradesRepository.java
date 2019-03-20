package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Doctor;
import com.myjpa.springboot.entity.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GradesRepository extends JpaRepository<Grades,Integer> {
    List<Grades> findByReferee_IdentityNum(String referee_id);
    @Transactional
    @Query(value = "select * from grades where athlete_id = ?1", nativeQuery = true)
    List<Grades> findByAthleteCompetition(Integer athleteId);
    @Transactional
    @Query(value = "select * from grades where competition_id = ?1", nativeQuery = true)
    List<Grades> findByCompetition_Id(Integer competitionId);
    @Transactional
    @Query(value = "select * from grades where referee_identity_num = ?1 and athlete_id = ?2 and competition_id = ?3", nativeQuery = true)
    List<Grades> findByReferee_IdentityNumAndAthlete_IdAndCompetition_Id(String referee_id,Integer athleteId,Integer competitionId);
    @Transactional
    @Query(value = "select * from grades where athlete_id = ?1 and competition_id = ?2", nativeQuery = true)
    List<Grades> findByAthlete_IdAndCompetition_Id(Integer athleteId,Integer competitionId);
}
