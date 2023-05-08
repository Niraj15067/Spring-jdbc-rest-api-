package com.student.dao;

import java.util.List;

import com.student.model.Student;

public interface StudentDao {

	public int saveStudent(Student s);

	public List<Student> getAllStudent();

	public Student getStudentById(int id);

	public String deleteStudent(int id);

	public int updateStudent(int id,Student s);

}
