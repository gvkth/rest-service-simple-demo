package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	static List<Student> theStudents;
	
	//define @PostConstruct to load the student data ... only once
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>(); 
		theStudents.add(new Student("A1","B1")); 
		theStudents.add(new Student("A2","B2"));
		theStudents.add(new Student("A3","B3"));
				 
	}
	
	/*
	 * static { theStudents = new ArrayList<>(); theStudents.add(new
	 * Student("A1","B1")); theStudents.add(new Student("A2","B2"));
	 * theStudents.add(new Student("A3","B3"));
	 * System.out.println("===================================abc"); }
	 */
	
	//define endpoint for "/students" - return list of students
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		return theStudents;
	}
	
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		if(studentId>=theStudents.size() || studentId<0) {
			throw new StudentNotFoundException("Student id not found - "+studentId);
		}
		return theStudents.get(studentId);
	}
	
	
	
}
