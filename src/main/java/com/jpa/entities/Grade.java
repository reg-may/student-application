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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name=Grade.ENTITY_NAME)
public class Grade {
	public static final String ENTITY_NAME = "trGrade";
	
	private Integer idNo;
	private BigDecimal midtermScore;
	private BigDecimal finalScore;
	private BigDecimal finalGrade;
	private List<Quiz> quizzes = new ArrayList<Quiz>();
	private List<Laboratory> laboratories = new ArrayList<Laboratory>();
	private List<Assignment> assignments = new ArrayList<Assignment>();
	private UserSubject userSubject;
	private Subject subject;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo() {
		return idNo;
	}
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	@Column(nullable=true)
	public BigDecimal getMidtermScore() {
		return midtermScore;
	}
	public void setMidtermScore(BigDecimal midtermScore) {
		this.midtermScore = midtermScore;
	}
	
	@Column(nullable=true)
	public BigDecimal getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(BigDecimal finalScore) {
		this.finalScore = finalScore;
	}
	
	@Column(nullable=true)
	public BigDecimal getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(BigDecimal finalGrade) {
		this.finalGrade = finalGrade;
	}
	
	//Quizzes
	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY, cascade={ CascadeType.ALL},targetEntity=Quiz.class, orphanRemoval=true)
	public List<Quiz> getQuizzes() {
		return quizzes;
	}
	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	
	//Laboratory
	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY, cascade={ CascadeType.ALL},targetEntity=Laboratory.class, orphanRemoval=true)
	public List<Laboratory> getLaboratories() {
		return laboratories;
	}
	public void setLaboratories(List<Laboratory> laboratories) {
		this.laboratories = laboratories;
	}

	//Assignment
	@OneToMany(mappedBy = "grade", fetch = FetchType.LAZY, cascade={ CascadeType.ALL},targetEntity=Assignment.class, orphanRemoval=true)
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	@OneToOne(mappedBy = "grade", fetch = FetchType.LAZY, cascade={ CascadeType.ALL},targetEntity=UserSubject.class, orphanRemoval=true)
	public UserSubject getUserSubject() {
		return userSubject;
	}
	public void setUserSubject(UserSubject userSubject) {
		this.userSubject = userSubject;
	}
	
	//Subject
	@JoinColumn(name = "subjectIdNo", updatable = true, nullable = true, insertable = true)//////////////////////////
	@OneToOne(targetEntity=Subject.class)
	public Subject getSubject() {
			return subject;
	}
	
	public void setSubject(Subject subject) {
			this.subject = subject;
	}
	
}
