package com.ejb.services.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.LaboratoryService;
import com.jpa.entities.Assignment;
import com.jpa.entities.Laboratory;
import com.jpa.entities.User;

@Stateless
public class LaboratoryServiceImpl implements LaboratoryService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3553042863069957763L;
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;
	
	@Override
	public void addLaboratory(Laboratory laboratory) {
		em.persist(laboratory);
	}
	
	@Override
	public void updateLaboratory(Laboratory laboratory){
		em.merge(laboratory);
		
	}

	@SuppressWarnings("unchecked")
	public List<Laboratory> getLaboratories(Laboratory laboratory){
		return (List<Laboratory>)em.createQuery("Select l from "+Laboratory.ENTITY_NAME+" l").getResultList();
	}

	public void removeLaboratory(Laboratory laboratory){
	
		laboratory = em.find(laboratory.getClass(), laboratory.getIdNo());
		em.remove(laboratory);
	}
	
 public List<Laboratory> getLaboratoriesAccordingtoGrade(int id){
		 
		 List<Laboratory> laboratories = new ArrayList<Laboratory>();
	     laboratories.add(em.find(Laboratory.class, id));
	     return laboratories;
	 }
 
	 public BigDecimal getLabScoreByGrade(int id){
	     return em.find(Laboratory.class, id).getScore();
	}
}
