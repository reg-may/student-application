package com.gui.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ejb.services.StudentService;
import com.ejb.services.UserSubjectService;
import com.jpa.entities.Student;
import com.jpa.entities.User;
import com.jpa.entities.UserSubject;


@ViewScoped
@Named("usersubjectGlobal") 
public class UserSubjectGlobal implements Serializable {

	private UserSubjectService Service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5907065065371668205L;
	

	private UserSubject userSubject;
	private User user;
	
	public UserSubject findUser(){

		return userSubject = Service.findUserIdNo(getUser().getIdNo());

	}

	public UserSubject getUserSubject() {
		return userSubject;
	}

	public void setUserSubject(UserSubject userSubject) {
		this.userSubject = userSubject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
		
}
