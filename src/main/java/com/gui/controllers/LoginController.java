package com.gui.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.ejb.services.UserService;
import com.jpa.entities.User;
import com.library.util.SessionUtils;



@ViewScoped
@Named("loginController") 
public class LoginController implements Serializable{
	private static final long serialVersionUID = -1440759894210785242L;
	
	@EJB
	private UserService service;
	
	@Inject private UserGlobal userGlobal;
	
	
	private User user = new User();
	private HttpSession session;	
    private Map<String,String> types;
	
	@PostConstruct
	public void init(){
		types  = new HashMap<String, String>();
        types.put("Teacher", "Teacher");
        types.put("Student", "Student");
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	
	public String validateUser(){
		User userNameDB = service.findUserName(getUser().getUserName());
		User passWordDB = service.findPassWord(getUser().getPassWord());
		
		String link ="";
//		String type ="Teacher";
//		System.out.println("Type: "+getUser().getType());
		System.out.println("Username: "+getUser().getUserName());
		System.out.println("Password: "+getUser().getPassWord());
		
		User typeDB = service.FindType(getUser().getType());

		if((userNameDB != null) && (passWordDB != null)){
			updateUserGlobal(getUser());
			System.out.println("USER "+getUser());
			
			if(types.containsValue(getUser().getType())){
				
				session = SessionUtils.getSession();
				session.setAttribute("userName", userNameDB);
				link = "teacher";
			}else{
				
				session = SessionUtils.getSession();
				session.setAttribute("userName", userNameDB);
				link = "studentMain";
			}
			
		}else{
			FacesContext.getCurrentInstance().addMessage("s", new FacesMessage("Invalid Input Fields", "err"));
			link = "index";
		}
		return link; 
				
	}
	
	public String logOut(){
		HttpSession session = SessionUtils.getSession();
//		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "index.xhtml";
	}
	
	public void updateUserGlobal(User user){
		FacesContext context = FacesContext.getCurrentInstance();
		
		userGlobal = (UserGlobal) context.getApplication().getExpressionFactory()
				.createValueExpression(context.getELContext(), "#{userGlobal}", 
						UserGlobal.class).getValue(context.getELContext());
		
		userGlobal.setUser(user);
	}



	public Map<String,String> getTypes() {
		return types;
	}



	public void setTypes(Map<String,String> types) {
		this.types = types;
	}
	
	

}
