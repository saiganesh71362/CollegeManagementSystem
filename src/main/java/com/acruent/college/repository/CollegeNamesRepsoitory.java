package com.acruent.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acruent.college.entity.CollegeNames;

@Repository
public interface CollegeNamesRepsoitory extends JpaRepository<CollegeNames, Integer> {

}
