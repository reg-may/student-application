package com.gui.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.jpa.entities.User;


@Named("userGlobal")
@SessionScoped
public class UserGlobal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	
	
}
