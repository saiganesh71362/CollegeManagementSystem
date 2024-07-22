package com.acruent.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acruent.college.entity.StudentBranch;

@Repository
public interface StudentBranchRepository extends JpaRepository<StudentBranch, Integer> {

}
