package com.acruent.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acruent.college.entity.CollegeNames;

@Service
public interface CollegeNamesService {
	
	public String newCollege(CollegeNames collegeNames);
	public CollegeNames getCollegeNameById(Integer id) throws Exception;
	public List<CollegeNames> getAllCollegeNames();
	public String updateCollegeNamesById(CollegeNames collegeNames, Integer id) throws Exception;
	public String deleteStudent(Integer id);

}
