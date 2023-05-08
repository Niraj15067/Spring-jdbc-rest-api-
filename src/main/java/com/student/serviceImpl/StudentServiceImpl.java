package com.student.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dao.StudentDao;
import com.student.model.Student;
import com.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentdao;

	@Override
	public int saveStudent(Student s) {
		// TODO Auto-generated method stub
		return studentdao.saveStudent(s);
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return studentdao.getAllStudent();
	}
	@Override
	public String deleteStudent(int id) {
		// TODO Auto-generated method stub
		return studentdao.deleteStudent(id);
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return studentdao.getStudentById(id);
	}

	@Override
	public int updateStudent(int id,Student s) {
		// TODO Auto-generated method stub
		return studentdao.updateStudent(id,s);
	}
	
	
}
