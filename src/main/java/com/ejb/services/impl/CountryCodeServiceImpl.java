package com.ejb.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.CountryCodeService;
import com.jpa.entities.CountryCode;




@Stateless
public class CountryCodeServiceImpl implements CountryCodeService, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3553042863069957763L;
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;
	
	//Add Country
	@Override
	public void addCountryCode(CountryCode countryCode) {
		em.persist(countryCode);
	}
	
	//Remove Country
	@Override
	public void removeCountryCode(CountryCode countryCode){
		countryCode = em.find(countryCode.getClass(), countryCode.getCountryId());
		em.remove(countryCode);
	}
	
	//Update 
	@Override
	public void updateCountryCode(CountryCode countryCode){
		em.merge(countryCode);
	}
	
	public EntityManager getEntityManager(){
		return em;
	}
	
	//View CountryCode
	@SuppressWarnings("unchecked")
	public List<CountryCode> getList(){
		return (List<CountryCode>)em.createQuery("Select c from "+CountryCode.ENTITY_NAME+" c").getResultList();			
	}
	
	public CountryCode findCode(Integer countryId){
		CountryCode countryCode = em.find(CountryCode.class, countryId);
		
		return  countryCode;
	}
//	//View CountryCode
//		@SuppressWarnings("unchecked")
//		public List<CountryCode> getCodeList(){
//			return (List<CountryCode>)em.createQuery("Select c.countrycode from "+CountryCode.ENTITY_NAME+" c").getResultList();			
//		}
}
