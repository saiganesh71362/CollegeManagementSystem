package com.acruent.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acruent.college.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
