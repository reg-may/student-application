package com.library.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	 
	public static HttpSession getSession(){
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();		
	}
	
	public static String getUserName(){
		HttpSession session = (HttpSession)
				FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("userName").toString();
	}
	
	public static String getPassWord(){
		HttpSession session = (HttpSession)
				FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("passWord").toString();
	}
	
	public static String getIdNo(){
		HttpSession session = getSession();
		if(session != null)
			return (String) session.getAttribute("idNo");
		else
			return null;
	}

}
