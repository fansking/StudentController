package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Integer> {
}
