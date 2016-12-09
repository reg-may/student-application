package com.jpa.entities;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;

@Entity(name=UserSubject.ENTITY_NAME)
public class UserSubject {
	public static final String ENTITY_NAME = "trUser_trSubject";

	
	private Integer idNo;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String birthDay;

	private String type;
	private String userName;
	private String passWord;
	
	private String name;
	private BigDecimal unit;
	private String description;
	private String assignedTo;
	
	private User user;
	private Grade grade;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "usersubjectId")
	public Integer getIdNo() {
		return idNo;
	}
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getMiddleName() {
//		return middleName;
//	}
//	public void setMiddleName(String middleName) {
//		this.middleName = middleName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public String getBirthDay() {
//		return birthDay;
//	}
//	public void setBirthDay(String birthDay) {
//		this.birthDay = birthDay;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getPassWord() {
//		return passWord;
//	}
//	public void setPassWord(String passWord) {
//		this.passWord = passWord;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public BigDecimal getUnit() {
//		return unit;
//	}
//	public void setUnit(BigDecimal unit) {
//		this.unit = unit;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getAssignedTo() {
//		return assignedTo;
//	}
//	public void setAssignedTo(String assignedTo) {
//		this.assignedTo = assignedTo;
//	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "useridNo")  
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "subjectidNo")
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "gradeidNo", insertable = true, updatable = true, nullable = true)
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	private Subject subject;
}
