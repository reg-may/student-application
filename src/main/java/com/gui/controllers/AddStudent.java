package com.gui.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ejb.services.CountryCodeService;
import com.ejb.services.StudentProfileService;
import com.ejb.services.StudentService;
import com.jpa.entities.CountryCode;
import com.jpa.entities.Student;
import com.jpa.entities.StudentProfile;
import com.poi.generator.StudentExcelGenerator;
import com.poi.generator.StudentProfileExcelGenerator;




@ViewScoped
@Named("addStudent")
public class AddStudent implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 681184924976386026L;
	
	private Student student = new Student();
	
	
	private List<Student> studentList;
	private List<StudentProfile> studentProfileList;
	
	private StudentProfile studentProfile = new StudentProfile();
	
	Map<String, Object[]> data = new TreeMap<String, Object[]>();

	//Blank workbook
    XSSFWorkbook studentbook = new XSSFWorkbook(); 
  //Create a blank sheet
    XSSFSheet sheet = studentbook.createSheet("Student Data");
  //This data needs to be written (Object[])
    
	
	//
	private String gender;
	private String birthDay;
	private String contactNo;
	private String emailAdd;
	private String yearLevel;
	private String section;
	
	private List<CountryCode> countrycodeList;
	
	private CountryCode countryCode;
	
	private Integer countryId;
	
	@PostConstruct
	public void init(){
		loadListOfCountryCode();
		
//		System.out.println("Student List: "+studentList.toString());
	}

	public List<Student> getStudentList(){
		return studentList;		
	}
	public List<StudentProfile> getStudentProfileList(){
		return studentProfileList;		
	}
	//
	@EJB
	private StudentService studentService;
	
	@EJB
	private CountryCodeService countryCodeService;
	
	@EJB
	private StudentProfileService studentProfileService;
	
	
	@Inject private StudentExcelGenerator studentExcelGenerator;
	@Inject private StudentProfileExcelGenerator studentProfileExcelGenerator;
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}
	
	// Profile Validation
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getYearLevel() {
		return yearLevel;
	}

	public void setYearLevel(String yearLevel) {
		this.yearLevel = yearLevel;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	//
	
	public void addStudentProfileList(){
		saveStudentProfileList();
	}
	
	private void saveStudentProfileList(){
//		if(gender == null || gender.isEmpty()){
//			FacesContext.getCurrentInstance().addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Gender"));
//		}else if(birthDay == null || birthDay.isEmpty()){
//			FacesContext.getCurrentInstance().addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Birthday"));
//		}else if(contactNo == null || contactNo.isEmpty()){
//			FacesContext.getCurrentInstance().addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Contact Number"));
//		}else if(emailAdd == null || emailAdd.isEmpty()){
//			FacesContext.getCurrentInstance().addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Email"));
//		}else if(yearLevel == null || yearLevel.isEmpty()){
//			FacesContext.getCurrentInstance().addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Year Level"));
//		}else if(section == null || section.isEmpty()){
//			FacesContext.getCurrentInstance().addMessage("null", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Section"));
//		}
//		else{
		System.out.println("CountryID "+getCountryId());
		countryCode = countryCodeService.findCode(getCountryId());
		getStudentProfile().setCountryCode(countryCode);
		getStudentProfile().setStudent(getStudent()); 
		getStudent().getStudentProfileList().add(getStudentProfile());
		studentProfile = new StudentProfile();
		countryCode = new CountryCode();
//		}
	}
	
	
	public String saveStudentWithDetails(){

		setStudent(student);
		studentService.addStudent(getStudent());
		return "studentList";
	}

	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}
	
	public List<CountryCode> getCountrycodeList() {
		return countrycodeList;
	}
	
	public void loadListOfCountryCode(){
		countrycodeList = countryCodeService.getList();
	}


	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	 private static final int DEFAULT_BUFFER_SIZE = 10240; 
	 private String filePath = "/home/lorenzor/student.xlsx";  
	         
	        public void downLoad() throws IOException {  
	             FacesContext context = FacesContext.getCurrentInstance();  
	            HttpServletResponse response = (HttpServletResponse) context  
	                         .getExternalContext().getResponse();  
	               File file = new File(filePath);  
	               
	            if (!file.exists()) {  
	                   response.sendError(HttpServletResponse.SC_NOT_FOUND);  
	                    return;  
	              }  
	               response.reset();  
	               response.setBufferSize(DEFAULT_BUFFER_SIZE);  
	               response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");  
	               response.setHeader("Content-Length", String.valueOf(file.length()));  
	               response.setHeader("Content-Disposition", "attachment;filename=\""  
	                      + file.getName() + "\"");  
	               
	              BufferedInputStream input = null;  
	              BufferedOutputStream output = null;  
	             try {  
	                   input = new BufferedInputStream(new FileInputStream(file),  
	                           DEFAULT_BUFFER_SIZE);  
	                 output = new BufferedOutputStream(response.getOutputStream(),  
	                              DEFAULT_BUFFER_SIZE);  
	                    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];  
	                    int length;  
	                    while ((length = input.read(buffer)) > 0) {  
	                        output.write(buffer, 0, length);  
	                    }  
	             } finally {  
	                 	input.close();  
	                    output.close();  
	         }  
	             context.responseComplete();  
	     }
////////////////////////////////////////////////////////////////////////////////////////////////////////	        
	        public void generateStudent(){
	        	studentList = studentService.getStudentList();
	    		FacesContext context = FacesContext.getCurrentInstance();
	    		
	    		studentExcelGenerator = (StudentExcelGenerator) context.getApplication().getExpressionFactory()
	    				.createValueExpression(context.getELContext(), "#{studentExcelGenerator}", 
	    						StudentExcelGenerator.class).getValue(context.getELContext());
	    		
	    		studentExcelGenerator.createExcel(studentList);
	    	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	        
	        public void generateProfile(){
	        	studentProfileList = studentProfileService.getStudentProfileList(studentProfile);
	    		FacesContext context = FacesContext.getCurrentInstance();
	    		
	    		studentProfileExcelGenerator = (StudentProfileExcelGenerator) context.getApplication().getExpressionFactory()
	    				.createValueExpression(context.getELContext(), "#{studentProfileGenerator}", 
	    						StudentProfileExcelGenerator.class).getValue(context.getELContext());
	    		
	    		studentProfileExcelGenerator.createExcel(studentProfileList);
	    	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void generateProfileandStudent(){
studentList = studentService.getStudentList();
studentProfileList = studentProfileService.getStudentProfileList(studentProfile);
FacesContext context = FacesContext.getCurrentInstance();

studentProfileExcelGenerator = (StudentProfileExcelGenerator) context.getApplication().getExpressionFactory()
.createValueExpression(context.getELContext(), "#{studentProfileGenerator}", 
StudentProfileExcelGenerator.class).getValue(context.getELContext());

studentProfileExcelGenerator.createStudentAndProfile(studentList, studentProfileList);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}//End
