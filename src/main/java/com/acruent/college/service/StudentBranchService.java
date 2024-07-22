package com.acruent.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acruent.college.entity.StudentBranch;

@Service
public interface StudentBranchService {
	
	public String newBranchAdd(StudentBranch studentBranch);
	public StudentBranch getBranchById(Integer id) throws Exception;
	public List<StudentBranch> getAllBranches();
	public String updateBranchById(StudentBranch studentBranch, Integer id) throws Exception;
	public String deleteBranchById(Integer id);

}
