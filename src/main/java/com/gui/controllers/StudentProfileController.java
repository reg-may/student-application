package com.gui.controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejb.services.StudentProfileService;
import com.ejb.services.StudentService;
import com.jpa.entities.Student;
import com.jpa.entities.StudentProfile;

@ViewScoped
@Named("studentProfileController") 
public class StudentProfileController implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7392423508041252492L;

	private Student student;
	private StudentProfile studentProfile = new StudentProfile();
	
//	private List<WorkExperience> workExperienceList;
	
	@Inject GlobalController globalController;
	
	@EJB
	private StudentProfileService service;
	
	@EJB 
	private StudentService studentService;

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}
	
	public void saveStudentProfile(){
		
		setStudent(globalController.getStudent());
		getStudentProfile().setStudent(getStudent());

		service.addStudentProfile(getStudentProfile());
		studentProfile = new StudentProfile();
	}

	public void findStudentWithDetails(){
		setStudent(globalController.getStudent());
		
		setStudent(studentService.findStudentWithDetails(getStudent()));

	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
