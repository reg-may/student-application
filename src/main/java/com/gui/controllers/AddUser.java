package com.gui.controllers;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.hibernate.Session;

import com.ejb.services.GradeService;
import com.ejb.services.StudentService;
import com.ejb.services.SubjectService;
import com.ejb.services.UserService;
import com.jpa.entities.CountryCode;
import com.jpa.entities.Grade;
import com.jpa.entities.Student;
import com.jpa.entities.StudentProfile;
import com.jpa.entities.Subject;
import com.jpa.entities.User;
import com.library.util.HibernateUtil;


@ViewScoped
@Named("addUser")
public class AddUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 681184924976386026L;
	
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String birthDay;
	private String type;
	private String userName;
	private String passWord;
	private Integer userId;
	private List<Subject> subjects;
	private List<User> users;
	private UploadedFile uploadedFile;
	private UploadedFile uploadedFileTemp;

	private List<Subject> subjectsTemp = new ArrayList<Subject>();
	private Boolean rendered;
	private Subject subject = new Subject();
	private User user = new User();
	private Grade grade = new Grade();
	@EJB
	private UserService userService;
	
	@EJB
	private SubjectService subjectService;
	@EJB
	private GradeService gradeService;
	private byte[] profilePicture;
	
	//STORING THE SUBJECT TO THE TEMPORARY LIST 
		public void addSubjectsTemp(){
			if(getSubject() != null){
				getSubjectsTemp().add(getSubject());			
			}
			//subject = new Subject();
		}

		@PostConstruct
		public void init(){
			loadListOfUsers();
			if(getUser() != null){
				setRendered(true);
				getRendered();
				System.out.println("Render"+getRendered());
			}else{
				setRendered(false);
				getRendered();
				System.out.println("Render 2"+getRendered());
			}
			
//			if(getUploadedFile() != null){
//				setUploadedFileTemp(getUploadedFile());///////////////////
//				System.out.println("TEMP: "+getUploadedFileTemp());
//			}
		}
		
		private void loadListOfUsers(){

				users = userService.FindUserAccordingToType("Student");		
		}
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addSubjects(){
		
		saveSubjects();
	}
	
	public void saveSubjects(){
			addSubjectsTemp();
//			getGrade().setFinalGrade(BigDecimal.valueOf(0));
//			getGrade().setMidtermScore(BigDecimal.valueOf(0));
//			getGrade().setFinalScore(BigDecimal.valueOf(0));
//			getSubject().setGrade(getGrade()); /////////////////////////////////////////////////////////////////////////
//			System.out.println("Final Score"+getSubject().getGrade().getFinalScore());
//			gradeService.addGrade(getGrade());///////////////////////////////////////////////////
			setSubject(getSubject()); 
			getUser().getSubjects().add(getSubject());
			subject = new Subject();
	}
	
	
//	public byte[] InputStreamToByte(InputStream fis) throws FileNotFoundException {
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();        
//		byte[] buf = new byte[1024];        
//		try {           
//		 for (int readNum; (readNum = fis.read(buf)) != -1;) {               
//		 bos.write(buf, 0, readNum);        
//		        System.out.println("read " + readNum + " bytes,");       
//		     }          
//		  byte[] bytes = bos.toByteArray();          
//		  return bytes;   
//		    
//		 }catch (IOException ex) {        
//		   ex.printStackTrace();         
//		   return null;        
//		  }  
//	}
	
	public String addUser(){
		System.out.println("HELLO");
		String link = "";
		try {
//			InputStream image = getUploadedFileTemp().getInputStream();/////////////////////
//			System.out.println("INPUTSTREAM"+image);
//		
//			byte[] imageByte = InputStreamToByte(image);
//			System.out.println("byte of Image: "+imageByte);
//			
//			getUser().setProfilePicture(imageByte);
			
			System.out.println("IMAGE: "+getUser().getProfilePicture());
			
			getUser().setType("Student");
			System.out.println("TYPE:"+getUser().getType());
			setUser(user);
			System.out.println("USER: "+getUser());
			userService.addUser(getUser());
			
			link = "addUser";
			System.out.println("LINK: "+link);
			
		} catch (Exception e) {
			
			System.out.println("ERROR: "+e);
		}
		
		return link;
		
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String Cancel(){
		 return "/addUser.xhtml?faces-redirect=true";
	}


	public List<Subject> getSubjectsTemp() {
		return subjectsTemp;
	}


	public void setSubjectsTemp(List<Subject> subjectsTemp) {
		this.subjectsTemp = subjectsTemp;
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public byte[] getProfilePicture() {
		return profilePicture;
	}


	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Boolean getRendered() {
		return rendered;
	}

	public void setRendered(Boolean rendered) {
		this.rendered = rendered;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public UploadedFile getUploadedFileTemp() {
		return uploadedFileTemp;
	}

	public void setUploadedFileTemp(UploadedFile uploadedFileTemp) {
		this.uploadedFileTemp = uploadedFileTemp;
	}

}
