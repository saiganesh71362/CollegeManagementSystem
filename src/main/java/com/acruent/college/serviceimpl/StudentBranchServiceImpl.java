package com.acruent.college.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acruent.college.entity.CollegeNames;
import com.acruent.college.entity.StudentBranch;
import com.acruent.college.repository.CollegeNamesRepsoitory;
import com.acruent.college.repository.StudentBranchRepository;
import com.acruent.college.service.StudentBranchService;

import jakarta.transaction.Transactional;

@Service
public class StudentBranchServiceImpl implements StudentBranchService {

	private final StudentBranchRepository studentBranchRepository;
	private final CollegeNamesRepsoitory collegeNamesRepsoitory;

	public StudentBranchServiceImpl(StudentBranchRepository studentBranchRepository,
			CollegeNamesRepsoitory collegeNamesRepsoitory) {
		this.studentBranchRepository = studentBranchRepository;
		this.collegeNamesRepsoitory = collegeNamesRepsoitory;
	}

	@Override
	@Transactional
	public String newBranchAdd(StudentBranch studentBranch) {
		CollegeNames college = studentBranch.getCollege();
		if (college != null && college.getId() != null) {
			Optional<CollegeNames> byId = collegeNamesRepsoitory.findById(college.getId());
			if (byId.isEmpty()) {
				return "College ID " + college.getId() + " not found";
			}
			studentBranch.setCollege(byId.get());
		} else {
			return "College information is required";
		}

		StudentBranch savedStudentBranch = studentBranchRepository.save(studentBranch);
		return "New Branch Created Successfully: " + savedStudentBranch.getId();
	}

	@Override
	public StudentBranch getBranchById(Integer id) throws Exception {
		Optional<StudentBranch> byId = studentBranchRepository.findById(id);
		if (byId.isPresent()) {
			return byId.get();
		} else
			throw new Exception("Thir Is NO Recode" + byId.get());
	}

	@Override
	public List<StudentBranch> getAllBranches() {
		List<StudentBranch> all = studentBranchRepository.findAll();
		return all;
	}

	@Override
	public String updateBranchById(StudentBranch studentBranch, Integer id) throws Exception {
		Optional<StudentBranch> existingBranches = studentBranchRepository.findById(id);
		if (existingBranches.isPresent()) {
			StudentBranch updateStudent = existingBranches.get();
			updateStudent.setBranchName(studentBranch.getBranchName());
			updateStudent.setStudents(studentBranch.getStudents());
			updateStudent.setCollege(studentBranch.getCollege());
			updateStudent.setUpdatedDate(studentBranch.getUpdatedDate());
			studentBranchRepository.save(updateStudent);

			return "Update Branch Success Fully :" + id;
		} else {
			throw new Exception("Their is No Id " + id);
		}

	}

	@Override
	public String deleteBranchById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
