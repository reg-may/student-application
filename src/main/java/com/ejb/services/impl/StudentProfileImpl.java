package com.ejb.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.StudentProfileService;
import com.jpa.entities.StudentProfile;

@Stateless
public class StudentProfileImpl implements StudentProfileService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager studentProf;
	
	@Override
	public void addStudentProfile(StudentProfile studentProfile) {
		studentProf.persist(studentProfile);
	}
	
	@Override
	public void updateStudentProfile(StudentProfile studentProfile){
		studentProf.merge(studentProfile);
		
	}

	@SuppressWarnings("unchecked")
	public List<StudentProfile> getStudentProfileList(StudentProfile studentProfile){
		return (List<StudentProfile>)studentProf.createQuery("Select s from "+StudentProfile.ENTITY_NAME+" s").getResultList();
	}

	public void removeStudentProfile(StudentProfile studentProfile){
	
		studentProfile = studentProf.find(studentProfile.getClass(), studentProfile.getProfileId());
		studentProf.remove(studentProfile);
	}
}
