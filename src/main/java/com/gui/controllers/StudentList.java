package com.gui.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ejb.services.StudentService;
import com.jpa.entities.Student;

@ViewScoped
@Named("studentList")
public class StudentList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1071129470037055448L;
	
	private List<Student> studentList;
	
	@EJB
	private StudentService service;
	
	
	@PostConstruct
	public void init(){
		loadListOfStudents();
	}
	
	private void loadListOfStudents(){
		studentList = service.getStudentList();
	}
	
	public List<Student> getStudentList(){
		return studentList;		
	}
	
	public String redirectPage(Student student){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("student", student);
		
		return "studentDetails";
		
	}
	
	public String redirectToAddStudent(){
		return "addStudent";
	}
	
}
