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

import com.acruent.college.entity.StudentBranch;
import com.acruent.college.service.StudentBranchService;


@RestController
@RequestMapping("/barnch")
public class BranchController {
	
	@Autowired
	private StudentBranchService studentBranchService;
	public BranchController(StudentBranchService studentBranchService) {
		this.studentBranchService = studentBranchService;
	}

	@PostMapping("/newBranch")
	public ResponseEntity<String> newBranchAdd(@RequestBody StudentBranch studentBranch) {
		String newBranchAdd = studentBranchService.newBranchAdd(studentBranch);

		return new ResponseEntity<String>(newBranchAdd, HttpStatus.CREATED);
	}

	@GetMapping("/getBranch/{id}")
	public ResponseEntity<StudentBranch> getBranchById(@PathVariable Integer id) throws Exception {
		StudentBranch branchById = studentBranchService.getBranchById(id);
		return new ResponseEntity<StudentBranch>(branchById, HttpStatus.OK);
	}

	@GetMapping("/allBranches")
	public ResponseEntity<List<StudentBranch>> getAllBranches() {
		List<StudentBranch> allBranches = studentBranchService.getAllBranches();
		return new ResponseEntity<List<StudentBranch>>(allBranches, HttpStatus.OK);
	}

	@PutMapping("updateBranch/{id}")
	public ResponseEntity<String> updateBranch(@RequestBody StudentBranch studentBranch, @PathVariable Integer id)
			throws Exception {
		String updateBranchById = studentBranchService.updateBranchById(studentBranch, id);

		return new ResponseEntity<String>(updateBranchById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteBranch/{id}")
	public ResponseEntity<String> deleteBranch(@PathVariable Integer id) {
		String deleteBranchById = studentBranchService.deleteBranchById(id);
		return new ResponseEntity<String>(deleteBranchById, HttpStatus.ACCEPTED);
	}
}
