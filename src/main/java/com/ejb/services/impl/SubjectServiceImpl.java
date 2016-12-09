package com.ejb.services.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.StudentService;
import com.ejb.services.SubjectService;
import com.jpa.entities.Student;
import com.jpa.entities.StudentProfile;
import com.jpa.entities.Subject;

@Stateless
public class SubjectServiceImpl implements SubjectService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3553042863069957763L;
	
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;
	
	@Override
	public void addSubject(Subject subject) {
		em.persist(subject);
	}
	
	@Override
	public void updateSubject(Subject subject){
		em.merge(subject);
		
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getSubjects(Subject subject){
		return (List<Subject>)em.createQuery("Select s from "+Subject.ENTITY_NAME+" s").getResultList();
	}

	public void removeSubject(Subject subject){
		subject = em.find(subject.getClass(), subject.getIdNo());
		em.remove(subject);
	}
	
	
	
	//Get grade according to id
	public Subject getGradeById(Subject subject){
		Integer id=subject.getIdNo();
		return (Subject)em.createQuery("Select g from grade g where g.subjectIdNo =:subjectIdNo" ).setParameter("subjectIdNo",id).setMaxResults(1).getSingleResult();
		
	}
	

}
