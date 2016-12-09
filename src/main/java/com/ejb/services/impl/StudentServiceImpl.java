package com.ejb.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.StudentService;
import com.jpa.entities.Student;


@Stateless
public class StudentServiceImpl implements StudentService, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3553042863069957763L;
	
	@PersistenceContext(unitName = "StudentApplication")
	private EntityManager em;
 
	//Add Student
	@Override
	public void addStudent(Student student) {
		em.persist(student);
	}
	
	//Remove Student
	@Override
	public void removeStudent(Student student){
		student = em.find(student.getClass(), student.getStudentId());
		
		student.getStudentProfileList().size();
		em.remove(student);
	}
	
	//Update Student
	public void updateStudent(Student student){
		em.merge(student);
	}
	
	public EntityManager getEntityManager(){
		return em;
	}
	
	//View Students
	@SuppressWarnings("unchecked")
	public List<Student> getStudentList(){
		return (List<Student>)em.createQuery("Select s from "+Student.ENTITY_NAME+" s").getResultList();			
	}
	
	//Get Profile of Student According to Student ID
	@SuppressWarnings("unchecked")
	public List<Student> getStudentListById(Student student){
		Integer id=student.getStudentId();
		return (List<Student>)em.createQuery("Select s from studentprofile s where s.studentId =:studentId" ).setParameter("studentId",id).getResultList();
		
	}
	
	
	//Get Student According to Student ID
		@SuppressWarnings("unchecked")
		public Student getStudentById(Student student){
			Integer id=student.getStudentId();
			return (Student) em.createQuery("Select s from student s where s.studentId =:studentId" ).setParameter("studentId",id).setMaxResults(1).getSingleResult();
			
		}
	
	
	//Get Student Profile Profile According to Student ID
	public Student findStudentWithDetails(Student student){
		Student studentid = em.find(student.getClass(), student.getStudentId());
		studentid.getStudentProfileList().size();  //FOR org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role... no session or session was closed 
		
		return studentid;
	}
	
	@Override
	public void findAndAddStudent(Student student){
		Student studentid = em.find(student.getClass(), student.getStudentId());
		em.merge(studentid);
	}
	
}
