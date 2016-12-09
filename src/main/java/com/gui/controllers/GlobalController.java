package com.gui.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ejb.services.StudentService;
import com.jpa.entities.Student;


@ViewScoped
@Named("globalController") 
public class GlobalController implements Serializable {
	
	private StudentService studentService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5907065065371668205L;
	
	private Student student;
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public void findStudentWithDetails(){

		student =  studentService.findStudentWithDetails(getStudent());

	}
}
