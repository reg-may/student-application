package com.ejb.services;

import java.util.List;

import com.jpa.entities.Subject;

public interface SubjectService {

	void addSubject(Subject subject);

	void updateSubject(Subject subject);
	
	List<Subject> getSubjects(Subject subject);
	
	void removeSubject(Subject subject);
	
//	Subject getGradeById(Subject subject);

}
