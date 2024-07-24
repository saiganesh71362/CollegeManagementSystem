package com.acruent.college.serviceimpl;

import java.util.ArrayList;
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
import com.acruent.college.repository.StudentBranchRepository;
import com.acruent.college.repository.StudentRepository;
import com.acruent.college.service.CollegeNamesService;

import jakarta.transaction.Transactional;

@Service
public class CollegeNamesServiceImpl implements CollegeNamesService {

	@Autowired
	private CollegeNamesRepsoitory collegeNamesRepository;
	@Autowired
	private StudentBranchRepository studentBranchRepository;
	@Autowired
	private StudentRepository studentRepository;
	
//	public CollegeNamesServiceImpl(CollegeNamesRepsoitory collegeNamesRepository,
//			StudentBranchRepository studentBranchRepository, StudentRepository studentRepository) {
//		this.collegeNamesRepository = collegeNamesRepository;
//		this.studentBranchRepository = studentBranchRepository;
//		this.studentRepository = studentRepository;
//	}





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

		CollegeNames savedCollege = collegeNamesRepository.save(collegeNames);
		return AppConstants.NEW_RECODE_ADD + savedCollege.getId();
	}

	@Override
	public CollegeNames getCollegeNameById(Integer id) throws Exception {
		Optional<CollegeNames> byId = collegeNamesRepository.findById(id);

		if (byId.isPresent()) {
			return byId.get();

		} else {
			throw new NoIdException(AppConstants.NO_RECORDES + id);  // Custom Exception Handle
		}
	}

	@Override
	public List<CollegeNames> getAllCollegeNames() {

		List<CollegeNames> all = collegeNamesRepository.findAll();
		return all;
	}
	
	@Override
    @Transactional
    public String updateCollegeNamesById(CollegeNames collegeNames, Integer id) throws Exception {
        Optional<CollegeNames> existingCollege = collegeNamesRepository.findById(id);
        if (existingCollege.isPresent()) {
            CollegeNames updateAccount = existingCollege.get();

            // Update the fields
            updateAccount.setCollegeName(collegeNames.getCollegeName());
            updateAccount.setUpdatedDate(collegeNames.getUpdatedDate());

            // Ensure all branches are saved or updated before setting them to CollegeNames
            List<StudentBranch> newBranches = collegeNames.getBranches();
            if (newBranches != null) {
                List<StudentBranch> updatedBranches = new ArrayList<>();

                for (StudentBranch newBranch : newBranches) {
                    StudentBranch branchToUpdate;
                    
                    if (newBranch.getId() != null) {
                        branchToUpdate = studentBranchRepository.findById(newBranch.getId()).orElse(newBranch);
                    } else {
                        branchToUpdate = newBranch;
                    }

                    branchToUpdate.setBranchName(newBranch.getBranchName());
                    branchToUpdate.setCollege(updateAccount);

                    // Save or update the branch first to avoid TransientPropertyValueException
                    branchToUpdate = studentBranchRepository.save(branchToUpdate);

                    // Ensure all students are saved or updated before setting them to StudentBranch
                    List<Student> newStudents = newBranch.getStudents();
                    if (newStudents != null) {
                        List<Student> updatedStudents = new ArrayList<>();

                        for (Student newStudent : newStudents) {
                            Student studentToUpdate;
                            
                            if (newStudent.getId() != null) {
                                studentToUpdate = studentRepository.findById(newStudent.getId()).orElse(newStudent);
                            } else {
                                studentToUpdate = newStudent;
                            }

                            studentToUpdate.setName(newStudent.getName());
                            studentToUpdate.setContact(newStudent.getContact());
                            studentToUpdate.setAddress(newStudent.getAddress());
                            studentToUpdate.setBranch(branchToUpdate);

                            // Save or update the student
                            studentRepository.save(studentToUpdate);
                            updatedStudents.add(studentToUpdate);
                        }
                        branchToUpdate.setStudents(updatedStudents);
                    }

                    updatedBranches.add(branchToUpdate);
                }
                updateAccount.setBranches(updatedBranches);
            }

            // Save the updated CollegeNames
            collegeNamesRepository.save(updateAccount);
            return AppConstants.UPDATE_RECORDS + id;
        } else {
            throw new NoRecordException(AppConstants.NO_RECORDS_ID + id); // Custom Exception
        }
    }


	@Override
	public String deleteStudent(Integer id) {
		Optional<CollegeNames> byId = collegeNamesRepository.findById(id);
		if (byId.isPresent()) {
			collegeNamesRepository.deleteById(id);
			return AppConstants.DELETE_RECORD_BY_ID + id;
		}

		return AppConstants.NO_RECORDES + id;
	}

}




//@Override
//@Transactional
//public String updateCollegeNamesById(CollegeNames collegeNames, Integer id) throws Exception {
//    Optional<CollegeNames> existingCollege = collegeNamesRepsoitory.findById(id);
//    if (existingCollege.isPresent()) {
//        CollegeNames updateAccount = existingCollege.get();
//
//        // Ensure all branches are saved before setting them to CollegeNames
//        List<StudentBranch> branches = collegeNames.getBranches();
//        if (branches != null) {
//            for (StudentBranch branch : branches) {
//                if (branch.getId() == null) {
//                	studentBranchRepository.save(branch); // Save new branch if not already saved
//                }
//            }
//        }
//
//        // Update the fields
//        updateAccount.setCollegeName(collegeNames.getCollegeName());
//        updateAccount.setBranches(branches);
//        updateAccount.setUpdatedDate(collegeNames.getUpdatedDate());
//
//        // Save the updated CollegeNames
//        collegeNamesRepsoitory.save(updateAccount);
//        return AppConstants.RECORD_ADD_SUCESSFULLY + id;
//
//    } else {
//        throw new NoRecordException(AppConstants.NO_RECORDS_ID + id); // Custom Exception
//    }
//}
//
//



// second 
//@Override
//@Transactional
//public String updateCollegeNamesById(CollegeNames collegeNames, Integer id) throws Exception {
//    Optional<CollegeNames> existingCollege = collegeNamesRepository.findById(id);
//    if (existingCollege.isPresent()) {
//        CollegeNames updateAccount = existingCollege.get();
//
//        // Ensure all branches are saved or updated before setting them to CollegeNames
//        List<StudentBranch> branches = collegeNames.getBranches();
//        if (branches != null) {
//            for (StudentBranch branch : branches) {
//                // Set the CollegeNames reference to ensure proper association
//                branch.setCollege(updateAccount);
//
//                if (branch.getId() == null) {
//                    studentBranchRepository.save(branch); // Save new branch if not already saved
//                } else {
//                    studentBranchRepository.saveAndFlush(branch); // Update existing branch
//                }
//            }
//        }
//
//        // Update the fields
//        updateAccount.setCollegeName(collegeNames.getCollegeName());
//        updateAccount.setBranches(branches);
//        updateAccount.setUpdatedDate(collegeNames.getUpdatedDate());
//
//        // Save the updated CollegeNames
//        collegeNamesRepository.save(updateAccount);
//        return AppConstants.UPDATE_RECORDS + id;
//
//    } else {
//        throw new NoRecordException(AppConstants.NO_RECORDS_ID + id); // Custom Exception
//    }
//}
//
//
