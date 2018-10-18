package com.myjpa.springboot.repository;

import com.myjpa.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    public List<Student> findByName(String name);
    public List<Student> findByAge(int age);
}
