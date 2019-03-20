package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition,String> {

}
