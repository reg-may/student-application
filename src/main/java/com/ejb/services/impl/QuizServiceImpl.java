package com.ejb.services.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.QuizService;
import com.jpa.entities.Grade;
import com.jpa.entities.Laboratory;
import com.jpa.entities.Quiz;
import com.jpa.entities.Subject;
import com.jpa.entities.User;

@Stateless
public class QuizServiceImpl implements QuizService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3553042863069957763L;
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;
	
	@Override
	public void addQuiz(Quiz quiz) {
		em.persist(quiz);
	}
	
	@Override
	public void updateQuiz(Quiz quiz){
		em.merge(quiz);
		
	}

	@SuppressWarnings("unchecked")
	public List<Quiz> getQuizzes(Quiz quiz){
		return (List<Quiz>)em.createQuery("Select q from "+Quiz.ENTITY_NAME+" q").getResultList();
	}

	public void removeQuiz(Quiz quiz){
	
		quiz = em.find(quiz.getClass(), quiz.getIdNo());
		em.remove(quiz);
	}
	
	//Get quiz according to gradeIdNo 
		public Quiz getQuizAccordingtoGrade(Grade grade){
			Integer id = grade.getIdNo();
			return (Quiz)em.createQuery("Select g from quiz g where g.grade.gradeIdNo =:g.grade.gradeIdNo" ).setParameter("g.grade.gradeIdNo",id).getResultList();
			
		}
		
		 public List<Quiz> getQuizzesAccordingtoGrade(int id){
			 
			 List<Quiz> quizzes = new ArrayList<Quiz>();
		     quizzes.add(em.find(Quiz.class, id));
		     return quizzes;
		 }
		 
		 public BigDecimal getQuizScoreByGrade(int id){
		     return em.find(Quiz.class, id).getScore();
		}
}
