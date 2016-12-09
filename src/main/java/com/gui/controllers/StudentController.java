package com.gui.controllers;


import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ejb.services.StudentService;
import com.jpa.entities.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
import org.primefaces.event.RowEditEvent;



@ViewScoped
@Named("studentController") 
public class StudentController implements Serializable {
	private static final long serialVersionUID = -1440759894210785242L;

	private Student student = new Student();	
	
	@EJB
	private StudentService studentService;
	
	private List<Student> studentList;
	
	private List<Student> student_rList;
	
	private Integer studentId_r;
	
	@PostConstruct
	public void init(){
		loadListOfStudents();
	}
	
	public void saveStudent() {
		
		studentService.addStudent(getStudent());;
		studentList = studentService.getStudentList();
		student = new Student();
	}
	
	public void deleteStudent(Student student){
		studentList.remove(student);
		studentService.removeStudent(student);
		
	}
	
	public void editStudent(Student student){
		studentService.updateStudent(student);
	}
	
	private void loadListOfStudents(){
		studentList = studentService.getStudentList();
	}
	
	public List<Student> getStudentList(){
		return studentList;		
	}
	
	public void onRowEdit(RowEditEvent event){
		
		editStudent((Student) event.getObject());
		
		FacesMessage msg = new FacesMessage("Student Edited", "Student Id No.: "+Integer.toString(((Student) event.getObject()).getStudentId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCancel(RowEditEvent event){
		FacesMessage msg = new FacesMessage("Edit Cancelled", "Student Id No.: "+Integer.toString(((Student) event.getObject()).getStudentId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void loadStudent_rList(Student student){
		student_rList = studentService.getStudentListById(student);
	}
 
	public List<Student> getStudent_rList(){
		return student_rList;
	}
	
//	public void printReport() throws  IOException, JRException{
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//		
//		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(getEmployeeList());
//		String reportPath = facesContext.getExternalContext().getRealPath("/reports/report_employee.jasper");
//		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(),beanCollectionDataSource);
//		
//		HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//		httpServletResponse.addHeader("Content-disposition","attachment; filename=statement.pdf");
//		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//		JasperExportManager.exportReportToPdfStream(jasperPrint,servletOutputStream);
//		facesContext.responseComplete();
//	}

	public Integer getStudentId() {
		return studentId_r;
	}

	public void setStudentId(Integer studentId_r) {
		this.studentId_r = studentId_r;
	}
	
	public void seId_rList(Integer studentId_r){
		this.studentId_r = studentId_r;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
