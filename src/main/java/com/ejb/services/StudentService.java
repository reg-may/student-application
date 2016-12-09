package com.ejb.services;

import java.util.List;

import javax.persistence.EntityManager;

import com.jpa.entities.Student;

public interface StudentService {

	void addStudent(Student student);

	void removeStudent(Student student);
	
	void updateStudent(Student student);
	
	EntityManager getEntityManager();
	
	List<Student> getStudentList();
	
	List<Student> getStudentListById(Student student);
	
	Student findStudentWithDetails(Student student);
	
	void findAndAddStudent(Student student);
	
	Student getStudentById(Student student);

}
