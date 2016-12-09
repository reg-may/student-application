package com.ejb.services;

import java.util.List;

import com.jpa.entities.Assignment;

public interface AssignmentService {

	void addAssignment(Assignment assignment);

	void updateAssignment(Assignment assignment);
	
	List<Assignment> getAssignments(Assignment assignment);
	
	void removeAssignment(Assignment assignment);
	
	List<Assignment> getAssignmentsAccordingtoGrade(int id);
}
