package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
