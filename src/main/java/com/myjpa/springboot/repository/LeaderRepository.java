package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderRepository extends JpaRepository<Leader,Integer> {
}
