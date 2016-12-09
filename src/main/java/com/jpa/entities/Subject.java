package com.jpa.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;




import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name=Subject.ENTITY_NAME)
public class Subject {
	public static final String ENTITY_NAME = "trSubject";
	
	private Integer idNo;
	private String name;
	private BigDecimal unit;
	private String description;
	private String assignedTo;
	private Grade grade;
	private List<User> users = new ArrayList<User>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo() {
		return idNo;
	}
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	
	@Column(nullable=true)
	public String getName() {
		return name;
	}
	public void setName(String subjectName) {
		this.name = subjectName;
	}
	
	@Column(nullable=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(nullable=false)
	public BigDecimal getUnit() {
		return unit;
	}
	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}
	
	@Column(nullable=false)
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	@OneToOne(mappedBy = "subject", fetch = FetchType.EAGER, cascade={ CascadeType.ALL},targetEntity=Grade.class, orphanRemoval=true)
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
		
}