package com.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name=StudentProfile.ENTITY_NAME)
public class StudentProfile {

	public static final String ENTITY_NAME = "studentprofile";
	
	private Integer profileId;
	private String gender;
	private String birthDay;
	private String contactNo;
	private String emailAdd;
	private String yearLevel;
	private String section;
	
	private Student student;
	private CountryCode countryCode;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}
	
	@Column(nullable=false)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(nullable=false)
	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	
	@Column(nullable=false)
	public String getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	@Column(nullable=false)
	public String getEmailAdd() {
		return emailAdd;
	}
	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}
		
	@Column(nullable=false)
	public String getYearLevel() {
		return yearLevel;
	}
	
	public void setYearLevel(String yearLevel) {
		this.yearLevel = yearLevel;
	}
	
	@Column(nullable=false)
	public String getSection() {
		return section;
	}
	
	public void setSection(String section) {
		this.section = section;
	}
	
	@JoinColumn(name = "studentId")
	@ManyToOne(targetEntity=Student.class)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@JoinColumn(name = "codeId")
	@ManyToOne(targetEntity=CountryCode.class)
	public CountryCode getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}	
}
