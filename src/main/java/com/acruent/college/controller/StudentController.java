package com.acruent.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acruent.college.entity.Student;
import com.acruent.college.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
    public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/newStudent")
	public ResponseEntity<String> newStudent(@RequestBody Student student) {
		String newStudent = studentService.newStudent(student);
		return new ResponseEntity<String>(newStudent, HttpStatus.CREATED);
	}

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer id) throws Exception {
		Student studentById = studentService.getStudentById(id);
		return new ResponseEntity<Student>(studentById, HttpStatus.OK);
	}

	@GetMapping("/getAllStudent")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> allStudents = studentService.getAllStudents();

		return new ResponseEntity<List<Student>>(allStudents, HttpStatus.OK);
	}

	@PutMapping("updateStudent/{id}")
	public ResponseEntity<String> updateStudentById(@RequestBody Student student, @PathVariable Integer id)
			throws Exception {
		String updateStudentById = studentService.updateStudentById(student, id);
		return new ResponseEntity<String>(updateStudentById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleStudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
		String deleteStudentById = studentService.deleteStudentById(id);

		return new ResponseEntity<String>(deleteStudentById, HttpStatus.OK);
	}
}
