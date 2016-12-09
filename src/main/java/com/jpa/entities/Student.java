package com.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name=Student.ENTITY_NAME)
public class Student {
	public static final String ENTITY_NAME = "student";
	
	
	private Integer studentId;
	private String firstName;
	private String lastName;
	
	private List<StudentProfile> studentProfileList = new ArrayList<StudentProfile>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	@Column(nullable=false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade={ CascadeType.ALL},targetEntity=StudentProfile.class, orphanRemoval=true)
	public List<StudentProfile> getStudentProfileList() {
		return studentProfileList;
	}
	public void setStudentProfileList(List<StudentProfile> studentProfileList) {
		this.studentProfileList = studentProfileList;
	}
		
}
