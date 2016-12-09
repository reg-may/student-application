package com.gui.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ejb.services.StudentService;
import com.ejb.services.UserService;
import com.jpa.entities.Student;
import com.jpa.entities.User;

@ViewScoped
@Named("userList")
public class UserList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1071129470037055448L;
	
	private List<User> users;
	private User user;
	@EJB
	private UserService service;
	
	
	@PostConstruct
	public void init(){
		loadListOfUsers();
	}
	
	private void loadListOfUsers(){
		
		User userType = service.FindType("Teacher");
	
		if(userType.equals(getUser())){
			users = service.FindUserAccordingToType("Teacher");	
		}else{
			users = service.FindUserAccordingToType("Student");	
		}
			
	}
	
	public List<User> getUsers(){
		return users;		
	}
	
	public String redirectPage(User user){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
		System.out.println("USER!!! "+user);
		return "userRecord";
		
	}
	
	public String redirectToAddUser(){
		return "addUser";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
