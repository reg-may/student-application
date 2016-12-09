package com.ejb.services.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.GradeService;
import com.jpa.entities.Grade;
import com.jpa.entities.Student;
import com.jpa.entities.Subject;


@Stateless
public class GradeServiceImpl implements GradeService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3553042863069957763L;
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;
 
	//Add Grade
	@Override
	public void addGrade(Grade grade) {
		em.persist(grade);
	}
	
	//Remove Grade
	@Override
	public void removeGrade(Grade grade){
		grade = em.find(grade.getClass(), grade.getIdNo());
		
		grade.getLaboratories().size();
		em.remove(grade);
	}
	
	//Update Grade
	public void updateGrade(Grade grade){
		em.merge(grade);
	}
	
	public EntityManager getEntityManager(){
		return em;
	}
	
	//View Grades
	@SuppressWarnings("unchecked")
	public List<Grade> getGrades(){
		return (List<Grade>)em.createQuery("Select g from "+Grade.ENTITY_NAME+" g").getResultList();			
	}
	
	//Get Quizzes of User According to ID
	@SuppressWarnings("unchecked")
	public List<Grade> getQuizzesById(Grade grade){
		Integer id=grade.getIdNo();
		return (List<Grade>)em.createQuery("Select q from quiz q where q.gradeIdNo =:gradeIdNo" ).setParameter("gradeIdNo",id).getResultList();
		
	}
	
		//Get Laboratories of User According to ID
		@SuppressWarnings("unchecked")
		public List<Grade> getLaboratoriesById(Grade grade){
			Integer id=grade.getIdNo();
			return (List<Grade>)em.createQuery("Select l from laboratory l where l.gradeIdNo =:gradeIdNo" ).setParameter("gradeIdNo",id).getResultList();
			
		}
	
		//Get Assignments of User According to ID
		@SuppressWarnings("unchecked")
		public List<Grade> getAssignmentsById(Grade grade){
				Integer id=grade.getIdNo();
				return (List<Grade>)em.createQuery("Select a from assignment a where a.gradeIdNo =:gradeIdNo" ).setParameter("gradeIdNo",id).getResultList();
					
		}
				
		//Get Grade According to ID
		@SuppressWarnings("unchecked")
		public Grade getGradeById(Grade grade){
			Integer id=grade.getIdNo();
			return (Grade) em.createQuery("Select g from grade g where g.idNo =:idNo" ).setParameter("idNo",id).setMaxResults(1).getSingleResult();
			
		}
		
		
		//Get Assignment According to ID of Grade
		public Grade findAssignment(Grade grade){
			Grade id = em.find(grade.getClass(), grade.getIdNo());
			id.getAssignments().size();  //FOR org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... no session or session was closed 
			
			return id;
		}
	
		//Get Lab According to ID of Grade
		public Grade findLab(Grade grade){
			Grade id = em.find(grade.getClass(), grade.getIdNo());
			id.getLaboratories().size();  //FOR org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... no session or session was closed 
			
			return id;
		}
	
		//Get Quiz According to ID of Grade
		public Grade findQuiz(Grade grade){
			Grade id = em.find(grade.getClass(), grade.getIdNo());
			id.getQuizzes().size();  //FOR org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... no session or session was closed 
					
			return id;
		}

	@Override
	public void findAndAddGrade(Grade grade){
		Grade id = em.find(grade.getClass(), grade.getIdNo());
		em.merge(id);
	}
	
	//Get Grades of User according to ID
		@SuppressWarnings("unchecked")
		public List<Grade> getGradesById(Grade grade){
			Integer id=grade.getIdNo();
			return (List<Grade>)em.createQuery("Select g from grade g where g.idNo =:idNo" ).setParameter("idNo",id).getResultList();
			
		}
		
		//Get grade according to id
		public Grade getSubjectGradeById(Subject subject){
			Integer id=subject.getIdNo();
			return (Grade)em.createQuery("Select g from grade g where g.subjectIdNo =:subjectIdNo" ).setParameter("subjectIdNo",id).setMaxResults(1).getSingleResult();
			
		}
}
