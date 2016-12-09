package com.gui.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.RowEditEvent;

import com.ejb.services.AssignmentService;
import com.ejb.services.GradeService;
import com.ejb.services.LaboratoryService;
import com.ejb.services.QuizService;
import com.ejb.services.SubjectService;
import com.ejb.services.UserService;
import com.ejb.services.UserSubjectService;
import com.jpa.entities.Assignment;
import com.jpa.entities.Grade;
import com.jpa.entities.Laboratory;
import com.jpa.entities.Quiz;
import com.jpa.entities.StudentProfile;
import com.jpa.entities.Subject;
import com.jpa.entities.User;
import com.jpa.entities.UserSubject;
import com.poi.generator.GradeExcelGenerator;
import com.poi.generator.StudentExcelGenerator;
import com.poi.generator.StudentProfileExcelGenerator;


@ViewScoped
@Named("addGrade")
public class AddGrade  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 681184924976386026L;
	
	private Integer idNo;
	private BigDecimal midtermScore;
	private BigDecimal finalScore;
	private BigDecimal finalGrade;
	
	
	private List<Quiz> quizzes;
	private List<Laboratory> laboratories;
	private List<Assignment> assignments;
	
	private List<Quiz> quizzesTemp = new ArrayList<Quiz>();
	private List<Laboratory> laboratoriesTemp = new ArrayList<Laboratory>();
	private List<Assignment> assignmentsTemp = new ArrayList<Assignment>();
	
	
	private Quiz quiz = new Quiz();
	private Assignment assignment = new Assignment();
	private Laboratory laboratory = new Laboratory();
	private UserSubject userSubject;
	
	private Subject subject;
	private Grade grade = new Grade(); ////////////////////////////////////
	private Integer index;
	private List<Grade> grades = new ArrayList<Grade>();
	@EJB
	private GradeService gradeService;
	
	@EJB
	private QuizService quizService;
	
	@EJB
	private LaboratoryService laboratoryService;
	
	@EJB
	private AssignmentService assignmentService;
	@EJB
	private UserSubjectService usersubService;
	
	private Boolean disabled = true;
	
	private BigDecimal assScore;
	private BigDecimal quizScore;
	private BigDecimal labScore;
	private User user;
	
	@Inject private GradeExcelGenerator gradeExcelGenerator;

	public BigDecimal getAssScore() {
		return assScore;
	}

	public void setAssScore(BigDecimal assScore) {
		this.assScore = assScore;
	}

	public BigDecimal getQuizScore() {
		return quizScore;
	}

	public void setQuizScore(BigDecimal quizScore) {
		this.quizScore = quizScore;
	}

	public BigDecimal getLabScore() {
		return labScore;
	}

	public void setLabScore(BigDecimal labScore) {
		this.labScore = labScore;
	}

	@PostConstruct
	public void init(){
		Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subject");
		Object userStudent = (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
		 System.out.println("Student: "+userStudent);
		 
		if(userStudent!=null){
			setUser((User) userStudent);
			 System.out.println("Student: "+getUser());
		}
		
		
		if(o!=null){
			setSubject((Subject) o);
			System.out.println("Subject: "+subject);
		}
		System.out.println("SUB GRADE"+getGrade());
//		loadGrade();
//		loadQuizzes();
//		loadAssignments();
//		loadLaboratories();
		
	}
	
	
	public void loadGrade(){
//		Grade userGrade = gradeService.getSubjectGradeById(getSubject());
		System.out.println("USER GRADE"+getSubject().getGrade());
		if(getSubject().getGrade() != null){
			grades.add(getSubject().getGrade());
		}
	}
	
	public Quiz getQuiz() {
		return quiz;
	}


	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}


	public Assignment getAssignment() {
		return assignment;
	}


	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}


	public Laboratory getLaboratory() {
		return laboratory;
	}


	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	public List<Quiz> getQuizzesTemp() {
		return quizzesTemp;
	}

	public void setQuizzesTemp(List<Quiz> quizzesTemp) {
		this.quizzesTemp = quizzesTemp;
	}

	public List<Laboratory> getLaboratoriesTemp() {
		return laboratoriesTemp;
	}

	public void setLaboratoriesTemp(List<Laboratory> laboratoriesTemp) {
		this.laboratoriesTemp = laboratoriesTemp;
	}

	public List<Assignment> getAssignmentsTemp() {
		return assignmentsTemp;
	}

	public void setAssignmentsTemp(List<Assignment> assignmentsTemp) {
		this.assignmentsTemp = assignmentsTemp;
	}
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}
	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	public List<Laboratory> getLaboratories() {
		return laboratories;
	}
	public void setLaboratories(List<Laboratory> laboratories) {
		this.laboratories = laboratories;
	}
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	public Integer getIdNo() {
		return idNo;
	}
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	public BigDecimal getMidtermScore() {
		return midtermScore;
	}
	public void setMidtermScore(BigDecimal midtermScore) {
		this.midtermScore = midtermScore;
	}
	public BigDecimal getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(BigDecimal finalScore) {
		this.finalScore = finalScore;
	}
	public BigDecimal getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(BigDecimal finalGrade) {
		this.finalGrade = finalGrade;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
		
	public String Cancel(){
		 return "/addGrade.xhtml?faces-redirect=true";
	}

	public UserSubject getUserSubject() {
		return userSubject;
	}

	public void setUserSubject(UserSubject userSubject) {
		this.userSubject = userSubject;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public void saveAssignments(){
		getAssignment().setGrade(getGrade()); 
		getGrade().getAssignments().add(getAssignment());
	//	getAssignmentsTemp().add(getAssignment());
		//assignment = new Assignment();
	}
	
	public void saveQuizzes(){
		getQuiz().setGrade(getGrade()); 
		getGrade().getQuizzes().add(getQuiz());
	//	getQuizzesTemp().add(getQuiz());
		//quiz = new Quiz();
	}
	
	public void saveLaboratories(){
		getLaboratory().setGrade(getGrade()); 
		getGrade().getLaboratories().add(getLaboratory());
	//	getLaboratoriesTemp().add(getLaboratory());
		//laboratory = new Laboratory();
	}
	
	public String saveGrade(){
		BigDecimal assScore = getAssignment().getScore().setScale(0, BigDecimal.ROUND_HALF_UP);
		BigDecimal quizScore = getQuiz().getScore().setScale(0, BigDecimal.ROUND_HALF_UP);
		BigDecimal labScore = getLaboratory().getScore().setScale(0, BigDecimal.ROUND_HALF_UP);
		BigDecimal midtermScore = getGrade().getMidtermScore().setScale(0, BigDecimal.ROUND_HALF_UP); 
		BigDecimal finalScore = getGrade().getFinalScore().setScale(0, RoundingMode.CEILING);
		
		System.out.println("ASS SCORE"+assScore);
		System.out.println("QUIZ SCORE"+quizScore);
		System.out.println("LAB SCORE"+labScore);
		System.out.println("MIDTERM SCORE"+midtermScore);
		System.out.println("FINAL SCORE"+finalScore);
		
		BigDecimal averageGrade = quizScore.add(assScore).add(labScore).add(midtermScore).add(finalScore);
		BigDecimal average = BigDecimal.valueOf(100).add(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(100));
		BigDecimal finalGrade = averageGrade.divide(average);
		
		getGrade().setFinalGrade(finalGrade.setScale(2, RoundingMode.CEILING));
		getGrade().setSubject(getSubject());
		setGrade(grade);
		gradeService.addGrade(getGrade());
		System.out.println("CHECK GRADE: "+getGrade());
		getGrade().setFinalGrade(finalGrade);
		////////////////////////////////////////////////////////////
		quiz = new Quiz();
		laboratory = new Laboratory();
		assignment = new Assignment();
		return "addGrade";
	}
	
	public void addNewLab(){
		
	}
	public void addNewAss(){
		
	}
	public void addNewQuiz(){
		
	}
	
	public void loadQuizzes(){
	List<Quiz> quiz = quizService.getQuizzesAccordingtoGrade(getSubject().getGrade().getIdNo());
	if(quiz!=null){
	setQuizzesTemp(quiz);
	System.out.println("QUIZZES: "+quizzesTemp);
	}
	}
	
	public void loadLaboratories(){
		List<Laboratory> laboratory = laboratoryService.getLaboratoriesAccordingtoGrade(getSubject().getGrade().getIdNo());
		if(laboratory!=null){
		setLaboratoriesTemp(laboratory);
		System.out.println("LABORATORIES: "+laboratoriesTemp);
		}
	}
	
	public void loadAssignments(){
		List<Assignment> assignment = assignmentService.getAssignmentsAccordingtoGrade(getSubject().getGrade().getIdNo());
		if(assignment!=null){
		setAssignmentsTemp(assignment);
		System.out.println("ASS: "+assignmentsTemp);
		}
	}
	
	
	//
	public void onRowEditAss(RowEditEvent event){
		assignmentService.updateAssignment((Assignment) event.getObject());
		
		Assignment ass = (Assignment) event.getObject();
		BigDecimal assScore = ass.getScore().setScale(0, BigDecimal.ROUND_HALF_UP);
		String assName =  ass.getName();
		
		
		BigDecimal lab = laboratoryService.getLabScoreByGrade(getSubject().getGrade().getIdNo());
		BigDecimal labScore = lab.setScale(0, BigDecimal.ROUND_HALF_UP);
		
		BigDecimal quiz = quizService.getQuizScoreByGrade(getSubject().getGrade().getIdNo());
		BigDecimal quizScore =quiz.setScale(0, BigDecimal.ROUND_HALF_UP);
		
		
		BigDecimal midtermScore = getGrades().get(0).getMidtermScore().setScale(0, BigDecimal.ROUND_HALF_UP); 
		BigDecimal finalScore = getGrades().get(0).getFinalScore().setScale(0, RoundingMode.CEILING);
		
		BigDecimal averageGrade = quizScore.add(assScore).add(labScore).add(midtermScore).add(finalScore);
		BigDecimal average = BigDecimal.valueOf(100).add(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(100));
		BigDecimal finalGrade = averageGrade.divide(average);
		////////////////////////////////////////////////////////////////////////
		getAssignment().setGrade(getGrade()); 
		getGrade().getAssignments().add(ass);
		getQuiz().setGrade(getGrade()); 
		getGrade().getQuizzes().add(getQuiz());
		getLaboratory().setGrade(getGrade()); 
		getGrade().getLaboratories().add(getLaboratory());
		getAssignment().setName(assName); ///////////////////////////////////////////
		///////////////////////////////////////////////////////////////////
		getGrade().setFinalGrade(finalGrade.setScale(2, RoundingMode.CEILING));
		getGrade().setIdNo(getSubject().getIdNo());////////////////////////////
		setGrade(getGrade());////////////////////////////////////////////
		gradeService.updateGrade(getGrade());

		FacesMessage msg = new FacesMessage("Subject Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	
	
	
	public void onRowEditLab(RowEditEvent event){
		laboratoryService.updateLaboratory((Laboratory) event.getObject());
		FacesMessage msg = new FacesMessage("Subject Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowEditQuiz(RowEditEvent event){
		quizService.updateQuiz((Quiz) event.getObject());
		FacesMessage msg = new FacesMessage("Subject Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCancel(RowEditEvent event){
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////	        
	public void generateStudentGradeRecord(){
	grades = gradeService.getGradesById(getGrade());
	
	
	FacesContext context = FacesContext.getCurrentInstance();
	
	gradeExcelGenerator = (GradeExcelGenerator) context.getApplication().getExpressionFactory()
	.createValueExpression(context.getELContext(), "#{gradeProfileGenerator}", 
	GradeExcelGenerator.class).getValue(context.getELContext());
	
	gradeExcelGenerator.createExcel(grades);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 private static final int DEFAULT_BUFFER_SIZE = 10240; 
	 private String filePath = "/C:/Users/Rappler M/studentgrade.xlsx";  
	         
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
}
