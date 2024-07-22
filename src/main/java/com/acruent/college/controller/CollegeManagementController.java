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

import com.acruent.college.entity.CollegeNames;
import com.acruent.college.service.CollegeNamesService;


@RestController
@RequestMapping("/college")
public class CollegeManagementController {
   
	@Autowired
	private CollegeNamesService collegeNamesService;
	public CollegeManagementController(CollegeNamesService collegeNamesService) {
		this.collegeNamesService = collegeNamesService;
	}

	@PostMapping("/newCollegeAdd")
	public ResponseEntity<String> newCollegeName(@RequestBody CollegeNames collegeNames) {
		String newCollege = collegeNamesService.newCollege(collegeNames);
		return new ResponseEntity<String>(newCollege, HttpStatus.CREATED);
	}

	@GetMapping("/getCollege/{id}")
	public ResponseEntity<CollegeNames> getCollegeById(@PathVariable Integer id) throws Exception {
		CollegeNames collegeNameById = collegeNamesService.getCollegeNameById(id);
		return new ResponseEntity<CollegeNames>(collegeNameById, HttpStatus.OK);
	}

	@GetMapping("/getAllColleges")
	public ResponseEntity<List<CollegeNames>> getAllCollegeNames() {
		List<CollegeNames> allCollegeNames = collegeNamesService.getAllCollegeNames();

		return new ResponseEntity<List<CollegeNames>>(allCollegeNames, HttpStatus.OK);
	}

	@PutMapping("updateCollegeNames/{id}")
	public ResponseEntity<String> updateCollegeNamesById(@RequestBody CollegeNames collegeNames,
			@PathVariable Integer id) throws Exception {
		String updateCollegeNamesById = collegeNamesService.updateCollegeNamesById(collegeNames, id);

		return new ResponseEntity<String>(updateCollegeNamesById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteCollegeById/{id}")
	public ResponseEntity<String> deleteCollegeName(@PathVariable Integer id) {
		String deleteStudent = collegeNamesService.deleteStudent(id);
		return new ResponseEntity<String>(deleteStudent, HttpStatus.OK);
	}

}
