package com.ejb.services;

import java.util.List;

import javax.persistence.EntityManager;

import com.jpa.entities.Grade;
import com.jpa.entities.Subject;

public interface GradeService {

	void addGrade(Grade grade);

	void removeGrade(Grade grade);

	void updateGrade(Grade grade);
	
	EntityManager getEntityManager();
	
	List<Grade> getGrades();
	
	List<Grade> getGradesById(Grade grade);
	
	List<Grade> getQuizzesById(Grade grade);
	
	List<Grade> getLaboratoriesById(Grade grade);
	
	List<Grade> getAssignmentsById(Grade grade);
	
	Grade getGradeById(Grade grade);
	
	Grade findAssignment(Grade grade);
	
	Grade findLab(Grade grade);
	
	Grade findQuiz(Grade grade);

	void findAndAddGrade(Grade grade);
	
	Grade getSubjectGradeById(Subject subject);
}
