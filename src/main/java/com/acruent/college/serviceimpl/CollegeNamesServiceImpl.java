package com.acruent.college.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acruent.college.appconstants.AppConstants;
import com.acruent.college.entity.CollegeNames;
import com.acruent.college.entity.Student;
import com.acruent.college.entity.StudentBranch;
import com.acruent.college.globalexceptionhandle.NoIdException;
import com.acruent.college.globalexceptionhandle.NoRecordException;
import com.acruent.college.repository.CollegeNamesRepsoitory;
import com.acruent.college.service.CollegeNamesService;

import jakarta.transaction.Transactional;

@Service
public class CollegeNamesServiceImpl implements CollegeNamesService {

	@Autowired
	private CollegeNamesRepsoitory collegeNamesRepsoitory;

	public CollegeNamesServiceImpl(CollegeNamesRepsoitory collegeNamesRepsoitory) {
		this.collegeNamesRepsoitory = collegeNamesRepsoitory;
	}

	@Override
	@Transactional
	public String newCollege(CollegeNames collegeNames)
	{
		for (StudentBranch branch : collegeNames.getBranches())
		{
			branch.setCollege(collegeNames); // Set the college reference in each branch
			for (Student student : branch.getStudents()) 
			{
				student.setBranch(branch); // Set the branch reference in each student
			}
		}

		CollegeNames savedCollege = collegeNamesRepsoitory.save(collegeNames);
		return AppConstants.NEW_RECODE_ADD + savedCollege.getId();
	}

	@Override
	public CollegeNames getCollegeNameById(Integer id) throws Exception {
		Optional<CollegeNames> byId = collegeNamesRepsoitory.findById(id);

		if (byId.isPresent()) {
			return byId.get();

		} else {
			throw new NoIdException(AppConstants.NO_RECORDES + id);  // Custom Exception Handle
		}
	}

	@Override
	public List<CollegeNames> getAllCollegeNames() {

		List<CollegeNames> all = collegeNamesRepsoitory.findAll();
		return all;
	}

	@Override
	public String updateCollegeNamesById(CollegeNames collegeNames, Integer id) throws Exception {

		Optional<CollegeNames> existingCollege = collegeNamesRepsoitory.findById(id);
		if (existingCollege.isPresent()) {
			CollegeNames updateAccount = existingCollege.get();

			updateAccount.setCollegeName(collegeNames.getCollegeName());
			updateAccount.setBranches(collegeNames.getBranches());
			updateAccount.setUpdatedDate(collegeNames.getUpdatedDate());
			collegeNamesRepsoitory.save(updateAccount);
			return AppConstants.RECORD_ADD_SUCESSFULLY + id;

		} else {
			throw new NoRecordException(AppConstants.NO_RECORDS_ID + id);  // Custom Exception
		}
	}

	@Override
	public String deleteStudent(Integer id) {
		Optional<CollegeNames> byId = collegeNamesRepsoitory.findById(id);
		if (byId.isPresent()) {
			collegeNamesRepsoitory.deleteById(id);
			return AppConstants.DELETE_RECORD_BY_ID + id;
		}

		return AppConstants.NO_RECORDES + id;
	}

}
