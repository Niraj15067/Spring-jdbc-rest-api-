package com.student.service;

import java.util.List;

import com.student.model.Student;

public interface StudentService {

	int saveStudent(Student s);

	List<Student> getAllStudent();

	Student getStudentById(int id);

	String deleteStudent(int id);

	int updateStudent(int id,Student s);

}
