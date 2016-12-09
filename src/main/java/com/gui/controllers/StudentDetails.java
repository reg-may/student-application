package com.gui.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.RowEditEvent;

import com.ejb.services.StudentProfileService;
import com.ejb.services.StudentService;
import com.jpa.entities.Student;
import com.jpa.entities.StudentProfile;
import com.poi.generator.StudentProfileExcelGenerator;

@ViewScoped
@Named("studentDetails")
public class StudentDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3743218582056536771L;

	private Student student;
	
	private StudentProfile studentProfile =  new StudentProfile();
	
	private List<StudentProfile> studentProfileList;
	
	private List<StudentProfile> studentProfileList_temp = new ArrayList<StudentProfile>();
	
	private List<StudentProfile> listProfiles;
	
	HashMap<Student, List<StudentProfile>> studentProfilesReport = new HashMap<Student, List<StudentProfile>>();
	 
	 private Student studentInfo;
	
	@EJB
	private StudentService studentService;
	
	@EJB
	private StudentProfileService studentProfileService;
	
	@Inject private StudentProfileExcelGenerator studentProfileExcelGenerator;
	
	private Boolean disabled = true;
	
	@PostConstruct
	public void init(){
		// STUDENT LIST GETTING THE DATA IN OBJECT
		Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("student");
		// 
		if(o!=null){
			//PARSING THE OBJECT O 
			setStudent( (Student) o);
			// LOADING LIST OF STUDENT PROFILES
			
			System.out.println("student.firstName: "+getStudent().getFirstName());
			loadListOfStudentProfile();
			//listProfiles = getListProfiles();
			//generateProfiles();
		}
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	// STUDENT PROFILES LOADER
	private void loadListOfStudentProfile(){

		student = studentService.findStudentWithDetails(getStudent());
		studentProfileList = student.getStudentProfileList();
	
		
	}
	
	public List<StudentProfile> getStudentProfileList() {
		return studentProfileList;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}
	
//	//ADDING THE NEWLY CREATED STUDENT PROFILE TO THE STUDENT PROFILE LIST
//	public void addStudentStudentProfile(){
//		if(getStudentProfile() != null){
//			getStudent().getStudentProfileList().add(getStudentProfile());			
//		}
//	}
	
	//STORING THE STUDENT PROFILE TO THE TEMPORARY LIST 
	public void addStudentStudentProfile_temp(){
		if(getStudentProfile() != null){
			getStudentProfileList_temp().add(getStudentProfile());			
		}
		studentProfile = new StudentProfile();
		setDisabled(false);
	}
	
	//CREATING NEW INSTANCE OF STUDENT'S PROFILE
	public void createNewStudentProfile(){
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setStudent(getStudent());
		
		setStudentProfile(studentProfile);
	}
	
	//LOADING LIST OF NEWLY ADDED PROFILE IN THE TABLE
	public List<StudentProfile> loadStudentProfileList_temp(){
		return getStudentProfileList_temp();
	}
	
	private List<StudentProfile> getStudentProfileList_temp(){
		return studentProfileList_temp;
	}
	
	//ADDING THE NEWLY CREATED STUDENT PROFILE TO THE EXISTING PROFILE LIST
	public void updateStudent(){	
		getStudent().getStudentProfileList().addAll(studentProfileList_temp);
		studentService.updateStudent(getStudent());
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Student Profile(s) successfully saved."));
		studentProfileList_temp = new ArrayList<StudentProfile>();
		setDisabled(true);
	}
	
	//
	public void onRowEdit(RowEditEvent event){
		
		studentProfileService.updateStudentProfile((StudentProfile) event.getObject());
				
		FacesMessage msg = new FacesMessage("Student Profile Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCancel(RowEditEvent event){
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	//REMOVE SELECTED STUDENT
	public void deleteStudentProfile(StudentProfile studentProfile){
		studentProfileService.removeStudentProfile(studentProfile);
		getStudent().getStudentProfileList().remove(studentProfile);
		setStudent(getStudent());
		loadListOfStudentProfile();
		FacesMessage msg = new FacesMessage("One row deleted");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public void generateProfiles(){

		String filePath = "/home/lorenzor/studentprofile.xlsx";
//		List<StudentProfile> studentProfiles = student.getStudentProfileList();
		FacesContext context = FacesContext.getCurrentInstance();

		studentProfileExcelGenerator = (StudentProfileExcelGenerator) context.getApplication().getExpressionFactory()
		.createValueExpression(context.getELContext(), "#{studentProfileGenerator}", 
		StudentProfileExcelGenerator.class).getValue(context.getELContext());
		
		try {
			setListProfiles(studentProfileExcelGenerator.readBooksFromExcelFile(filePath));
//			listProfiles = getListProfiles();
			System.out.println("LIST OF PROFILES: "+listProfiles.get(0).getGender());
			System.out.println("Success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public List<StudentProfile> getListProfiles() {
		return listProfiles;
	}

	public void setListProfiles(List<StudentProfile> listProfiles) {
		this.listProfiles = listProfiles;
	}
	
	public void jasperReport() throws  IOException, JRException{
		studentInfo = studentService.getStudentById(getStudent());
		
		studentProfilesReport.put(studentInfo, getStudent().getStudentProfileList());
		
//		System.out.println("STUDENT: "+studentInfo.getFirstName()+" PROFILE: "+getStudent().getStudentProfileList().get(1).getSection());
//		
//		System.out.println(new File("/reports/studentProfiles.jasper").exists());  
//		System.out.println(new File("/reports/studentProfiles.jasper").canRead());
//		System.out.println(new File("studentProfiles.jasper").getAbsolutePath());
//		
//		System.out.println("Hashmap Values"+studentProfilesReport.values().size());
//		System.out.println("Hashmap Keys"+studentProfilesReport.keySet().size());
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//studentProfilesReport.put(getStudent(), getStudentProfileList());
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(getStudent()));
		String reportPath = facesContext.getExternalContext().getRealPath("/reports/studentProfiles.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(),beanCollectionDataSource);
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition","attachment; filename=statement.pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,servletOutputStream);
		facesContext.responseComplete();
	}

	public Student getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(Student studentInfo) {
		this.studentInfo = studentInfo;
	}

	


}
