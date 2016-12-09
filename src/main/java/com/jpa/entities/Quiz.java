package com.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name=Quiz.ENTITY_NAME)
public class Quiz {
	public static final String ENTITY_NAME = "trQuiz";
	
	private Integer idNo;
	private String  name;
	private BigDecimal score;
	private Grade grade;
	
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
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false)
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	@JoinColumn(name = "gradeIdNo")
	@ManyToOne(targetEntity=Grade.class)
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	

}