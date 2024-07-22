package com.acruent.college.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acruent.college.entity.Student;
import com.acruent.college.entity.StudentBranch;
import com.acruent.college.repository.StudentBranchRepository;
import com.acruent.college.repository.StudentRepository;
import com.acruent.college.service.StudentService;

import jakarta.transaction.Transactional;


@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	private final StudentBranchRepository studentBranchRepository;


	public StudentServiceImpl(StudentRepository studentRepository, StudentBranchRepository studentBranchRepository) {
		this.studentRepository = studentRepository;
		this.studentBranchRepository = studentBranchRepository;
	}

	@Override
	@Transactional
	public String newStudent(Student student) {
	    StudentBranch branch = student.getBranch();
	    
	    if (branch != null && branch.getId() != null) {
	        Optional<StudentBranch> optionalBranch = studentBranchRepository.findById(branch.getId());
	        if (optionalBranch.isEmpty()) {
	            return "Branch with ID " + branch.getId() + " not found";
	        }
	        student.setBranch(optionalBranch.get());
	    } else {
	        return "Branch information is required";
	    }

	    Student savedStudent = studentRepository.save(student);
	    return "New Student Created Successfully: " + savedStudent.getId();
	}

	@Override
	public Student getStudentById(Integer id) throws Exception
	{
		Optional<Student> byId = studentRepository.findById(id);
		if(byId.isPresent())
		{
			return byId.get();
		}
		else
		{
		 throw new Exception("Thier Is No Id :"+id);
		}
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> all = studentRepository.findAll();
		return all;
	}

	@Override
	public String updateStudentById(Student student, Integer id) throws Exception
	{
		Optional<Student> existingStudent = studentRepository.findById(id);
		if(existingStudent.isPresent())
		{
			Student updateStudent = existingStudent.get();
			
			updateStudent.setName(student.getName());
			updateStudent.setContact(student.getContact());
			updateStudent.setAddress(student.getAddress());
			updateStudent.setBranch(student.getBranch());
			updateStudent.setUpdatedDate(student.getUpdatedDate());
			studentRepository.save(updateStudent);
			return "Update Student By Id :"+id;
		}
		else
		{
			 throw new Exception("Thir Is No Student :"+id);

		}
		
	}

	@Override
	public String deleteStudentById(Integer id)
	{
		Optional<Student> byId = studentRepository.findById(id);
		if(byId.isPresent())
		{
			studentRepository.deleteById(id);
			return "Record Delete Success Fully :"+byId.get();
		}
		
		return "Thair Is No Record :"+byId.get();
	}

}






//@Override
//@Transactional
//public String newStudent(Student student) 
//{
//	StudentBranch branch = student.getBranch();
//	if(branch != null && branch.getId() !=null)
//	{
//		StudentBranch orElse = studentBranchRepository.findById(branch.getId()).orElse(null);
//	    if(orElse == null)
//	    {
//			return "Category with ID " + student.getBranch().getId() + " not found";
//
//	    }
//	    student.setBranch(branch);
//		
//		Student save = studentRepository.save(student);
//		
//		return "New Record Created Success Fully :"+save.getId();
//		
//	}
//	else {
//		Optional<Student> byId = studentRepository.findById(student.getId());
//		if(byId.isPresent())
//		{
//			return "Id Already Exists :"+student.getId();
//		}
//		else
//		{
//			Student save = studentRepository.save(student);
//			
//			return "New Record Created Success Fully :"+save.getId();
//			
//		}
//	}
//}
//
//

