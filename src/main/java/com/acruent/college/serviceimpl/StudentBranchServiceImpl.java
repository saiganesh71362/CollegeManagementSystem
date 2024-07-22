package com.acruent.college.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acruent.college.appconstants.AppConstants;
import com.acruent.college.entity.CollegeNames;
import com.acruent.college.entity.StudentBranch;
import com.acruent.college.globalexceptionhandle.NoIdException;
import com.acruent.college.globalexceptionhandle.NoRecordException;
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
				return AppConstants.ID + college.getId() + AppConstants.NOT_FOUND;
			}
			studentBranch.setCollege(byId.get());
		} else {
			return AppConstants.INFORMATION_RER;
		}

		StudentBranch savedStudentBranch = studentBranchRepository.save(studentBranch);
		return AppConstants.NEW_RECODE_ADD + savedStudentBranch.getId();
	}

	@Override
	public StudentBranch getBranchById(Integer id) throws Exception {
		Optional<StudentBranch> byId = studentBranchRepository.findById(id);
		if (byId.isPresent()) {
			return byId.get();
		} else
			throw new NoIdException(AppConstants.NO_RECORDES + byId.get());
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

			return AppConstants.UPDATE_RECORDS + id;
		} else {
			throw new NoRecordException(AppConstants.NO_RECORDES + id);
		}

	}

	@Override
	public String deleteBranchById(Integer id)
	{
		Optional<StudentBranch> byId = studentBranchRepository.findById(id);
		if(byId.isPresent())
		{
			studentBranchRepository.deleteById(id);
			return AppConstants.DELETE_RECORD_BY_ID+id;
		}
		return AppConstants.NO_RECORDES+id;
	}

}
