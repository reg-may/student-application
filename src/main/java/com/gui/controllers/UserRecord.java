package com.gui.controllers;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.ejb.services.SubjectService;
import com.ejb.services.UserService;
import com.jpa.entities.Subject;
import com.jpa.entities.User;


@ViewScoped
@Named("userRecord")
public class UserRecord  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3743218582056536771L;
	private User user;
	private Subject subject = new Subject();
	private List<Subject> subjects = new ArrayList<Subject>();
	private List<Subject> subjectsTemp = new ArrayList<Subject>();
	
	@EJB
	private UserService userService;
	
	@EJB
	private SubjectService subjectService;
	
	private Boolean disabled = true;
	
	@PostConstruct
	public void init(){
		Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		 
		if(o!=null){
			setUser( (User) o);
			loadListOfSubjects();
		}
	}
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	// SUBJECT LOADER
		private void loadListOfSubjects(){

			user = userService.findUserSubjectsAccordingtoID(getUser());
			subjects = user.getSubjects();
			System.out.println("SUBJECTS: "+subjects);
			
		}
		
		//STORING THE SUBJECTS TO THE TEMPORARY LIST 
		public void addSubjectsTemp(){
			if(getSubject() != null){
				//getSubjectsTemp().add(getSubject());	
				System.out.println("Subjects Temp: "+subjectsTemp);
			}
			subject = new Subject();
			setDisabled(false);
		}
		
		//CREATING NEW INSTANCE OF SUBJECT
		public void createNewSubject(){
			Subject subject = new Subject();
			getUser().getSubjects().add(subject);
			setSubject(subject);
		}
		
		//ADDING THE NEWLY CREATED SUBJECT TO THE SUBJECTS
		public void updateUserSubjects(){	
			getUser().getSubjects().addAll(subjectsTemp);
			
			System.out.println("CURRENT SUBJECTS"+getUser().getSubjects().size());
			System.out.println("TEMP SUBJECT"+subjectsTemp);
			userService.updateUser(getUser());
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Subject(s) successfully saved."));
			subjectsTemp = new ArrayList<Subject>();
			setDisabled(true);
		}
		
		//
		public void onRowEdit(RowEditEvent event){
			User userId = userService.findUserSubjectsAccordingtoID(getUser());
			subjectService.updateSubject((Subject) event.getObject());
					
			FacesMessage msg = new FacesMessage("Subject Edited");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		public void onRowCancel(RowEditEvent event){
			FacesMessage msg = new FacesMessage("Edit Cancelled");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		//REMOVE SELECTED Subject
		public void deleteSubject(Subject subject){
			subjectService.removeSubject(subject);
			getUser().getSubjects().remove(subject);
			setUser(getUser());
			loadListOfSubjects();
			FacesMessage msg = new FacesMessage("One row deleted");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public List<Subject> getSubjects() {
		return subjects;
	}


	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public List<Subject> getSubjectsTemp() {
		return subjectsTemp;
	}

	public void setSubjectsTemp(List<Subject> subjectsTemp) {
		this.subjectsTemp = subjectsTemp;
	}
	
	public List<Subject> loadSubjectsTemp(){
		return getSubjectsTemp();
	}
	
	public String redirectPage(Subject subject){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("subject", subject);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
		System.out.println("Student 1111:"+user);
		System.out.println("Subject 1111:"+subject);
		return "addGrade";
		
	}
}
