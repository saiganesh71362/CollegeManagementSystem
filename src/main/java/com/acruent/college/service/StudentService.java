package com.acruent.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acruent.college.entity.Student;

@Service
public interface StudentService
{

	public String newStudent(Student student);
	public Student getStudentById(Integer id) throws Exception;
	public List<Student> getAllStudents();
	public String updateStudentById(Student student , Integer id) throws Exception;
	public String deleteStudentById(Integer id);
}
