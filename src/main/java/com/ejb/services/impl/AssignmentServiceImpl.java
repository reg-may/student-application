package com.ejb.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.AssignmentService;
import com.jpa.entities.Assignment;
import com.jpa.entities.Quiz;
import com.jpa.entities.User;


@Stateless
public class AssignmentServiceImpl implements AssignmentService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3553042863069957763L;
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;
	
	@Override
	public void addAssignment(Assignment assignment) {
		em.persist(assignment);
	}
	
	@Override
	public void updateAssignment(Assignment assignment){
		em.merge(assignment);
		
	}

	@SuppressWarnings("unchecked")
	public List<Assignment> getAssignments(Assignment assignment){
		return (List<Assignment>)em.createQuery("Select a from "+Assignment.ENTITY_NAME+" a").getResultList();
	}

	public void removeAssignment(Assignment assignment){
	
		assignment = em.find(assignment.getClass(), assignment.getIdNo());
		em.remove(assignment);
	}
	
	 public List<Assignment> getAssignmentsAccordingtoGrade(int id){
		 
		 List<Assignment> assignments = new ArrayList<Assignment>();
	     assignments.add(em.find(Assignment.class, id));
	     return assignments;
	 }
}
