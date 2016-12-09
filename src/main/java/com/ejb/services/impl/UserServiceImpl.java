package com.ejb.services.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.UserService;
import com.jpa.entities.Grade;
import com.jpa.entities.Student;
import com.jpa.entities.Subject;
import com.jpa.entities.User;

@Stateless
public class UserServiceImpl implements UserService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3553042863069957763L;
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;
 
	//Add User
	@Override
	public void addUser(User user) {
		em.persist(user);
	}
	
	//Remove User
	@Override
	public void removeUser(User user){
		user = em.find(user.getClass(), user.getIdNo());
		
		//user.getSubjects().size();
		em.remove(user);
	}
	
	//Update User
	public void updateUser(User user){
		em.merge(user);
	}
	
	public EntityManager getEntityManager(){
		return em;
	}
	
	//View Users
		@SuppressWarnings("unchecked")
		public List<User> getUsers(){
			return (List<User>)em.createQuery("Select u from "+User.ENTITY_NAME+" u").getResultList();			
		}
		
		@SuppressWarnings("unchecked")
		public List<User> FindUserAccordingToType(String type){
			return (List<User>)em.createQuery("select u from "+ User.ENTITY_NAME +" u where u.type = :type")
					.setParameter("type", type).getResultList();
		}
		
		 public byte[] loadImage(int id){
		        return em.find(User.class, id).getProfilePicture();
		 }
		
//	//Get Subjects of User According to ID
//	@SuppressWarnings("unchecked")
//	public List<Subject> getSubjects(User user){
//		Integer id=user.getIdNo();
//		return (List<Subject>)em.createQuery("Select s from subject s where s.userIdNo =:userIdNo" ).setParameter("userIdNo",id).getResultList();
//			
//	}
		//Get Student Profile Profile According to Student ID
		public User findUserSubjectsAccordingtoID(User user){
			User userid = em.find(user.getClass(), user.getIdNo());
			userid.getSubjects().size();  //FOR org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... no session or session was closed 
			
			return userid;
		}
	
	public User FindType(String type){
		return (User) em.createQuery("select u from "+ User.ENTITY_NAME +" u where u.type = :type")
				.setParameter("type", type).setMaxResults(1).getSingleResult();
	}
	
	public User findUserName(String userName){
		return (User) em.createQuery("select u from "+ User.ENTITY_NAME +" u where u.userName = :userName")
				.setParameter("userName", userName).setMaxResults(1).getSingleResult();
	}
	
	public User findPassWord(String passWord){
		return (User) em.createQuery("select p from "+ User.ENTITY_NAME +" p where p.passWord = :passWord")
				.setParameter("passWord", passWord).setMaxResults(1).getSingleResult();
	}
}
