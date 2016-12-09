package com.ejb.services;

import java.math.BigDecimal;
import java.util.List;

import com.jpa.entities.Grade;
import com.jpa.entities.Quiz;

public interface QuizService {

	void addQuiz(Quiz quiz);

	void updateQuiz(Quiz quiz);
	
	List<Quiz> getQuizzes(Quiz quiz);
	
	void removeQuiz(Quiz quiz);
	
	 Quiz getQuizAccordingtoGrade(Grade grade);
	 
	 List<Quiz> getQuizzesAccordingtoGrade(int id);
	 
	 public BigDecimal getQuizScoreByGrade(int id);
}
