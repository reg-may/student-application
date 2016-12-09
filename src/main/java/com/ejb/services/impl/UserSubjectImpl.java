package com.ejb.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.StudentService;
import com.ejb.services.UserSubjectService;
import com.jpa.entities.StudentProfile;
import com.jpa.entities.User;
import com.jpa.entities.UserSubject;

@Stateless
public class UserSubjectImpl implements UserSubjectService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;

	public UserSubject findUserIdNo(Integer userIdNo){
		return (UserSubject) em.createQuery("select u from "+ UserSubject.ENTITY_NAME +" u where u.userIdNo = :userIdNo")
				.setParameter("userIdNo", userIdNo).getSingleResult();
	}
	
	public UserSubject findSubjectIdNo(Integer subjectIdNo){
		return (UserSubject) em.createQuery("select s from "+ UserSubject.ENTITY_NAME +" s where s.subjectidno = :subjectIdNo")
				.setParameter("subjectidno", subjectIdNo).setMaxResults(1).getSingleResult();
	}
	
	public UserSubject findSubject(Integer subjectIdNo){
		return (UserSubject) em.createQuery("select s from "+ UserSubject.ENTITY_NAME +" s where s.subjectIdNo = :subjectIdNo")
				.setParameter("subjectIdNo", subjectIdNo).setMaxResults(1).getSingleResult();
	}
}
