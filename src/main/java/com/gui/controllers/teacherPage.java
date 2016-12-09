package com.gui.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.jpa.entities.User;
import com.library.util.SessionUtils;


@ViewScoped
@Named("teacherPage")
public class teacherPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4038509339364272164L;
	
	@Inject private UserGlobal userGlobal;
	@Inject private LoginController login;
	private User user;
	
	@PostConstruct
	public void init(){
		loadUser();
		
		
	}
	
	private void loadUser(){
		setUser(getUserGlobal());
	}
	
	
	public User getUserGlobal(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		userGlobal = (UserGlobal) context.getApplication().getExpressionFactory()
				.createValueExpression(context.getELContext(), "#{userGlobal}", 
						UserGlobal.class).getValue(context.getELContext());
		
		 return userGlobal.getUser();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}
	
	public String addStudent(){
		return "addUser";
	}
	
	public String showStudents(){
		return "userList";
	}
}
