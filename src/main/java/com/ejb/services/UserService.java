package com.ejb.services;

import java.util.List;

import javax.persistence.EntityManager;

import com.jpa.entities.User;

public interface UserService {

	void addUser(User user);

	void removeUser(User user);
	
	void updateUser(User user);
	
	EntityManager getEntityManager();
	
	List<User> getUsers();
	
	User FindType(String type);
	
	User findUserName(String userName);
	
	User findPassWord(String passWord);
	
	User findUserSubjectsAccordingtoID(User user);
	
	List<User> FindUserAccordingToType(String type);
	
	public byte[] loadImage(int id);
}
