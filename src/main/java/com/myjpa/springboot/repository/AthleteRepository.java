package com.myjpa.springboot.repository;


import com.myjpa.springboot.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete,Integer> {
    public List<Athlete> findByName(String name);
    public List<Athlete> findByAge(int age);


    List<Athlete> findByTeam_Id(Integer id);
}
