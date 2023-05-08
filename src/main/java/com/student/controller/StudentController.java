package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Student;
import com.student.service.StudentService;

@RestController
@RequestMapping("/stu")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student")
	public int saveStudent(@RequestBody Student s)
	{
		return studentService.saveStudent(s);
	}
	
	@GetMapping("/student")
	public List<Student> getAllStudent()
	{
		return studentService.getAllStudent();
	}
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id)
	{
		return studentService.getStudentById(id);
	}
	@DeleteMapping("/student/{id}")
	@ResponseBody
	public String deleteStudent(@PathVariable	int id)
	{
		return studentService.deleteStudent(id);
	}
	@PutMapping("/student/{id}")
	public int updateStudent(@PathVariable int id,@RequestBody Student s)
	{
		return studentService.updateStudent(id,s);
	}
	
}
