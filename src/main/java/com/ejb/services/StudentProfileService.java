package com.ejb.services;

import java.util.List;

import com.jpa.entities.StudentProfile;

public interface StudentProfileService {

	void addStudentProfile(StudentProfile studentProfile);

	void updateStudentProfile(StudentProfile studentProfile);
	
	List<StudentProfile> getStudentProfileList(StudentProfile studentProfile);
	
	void removeStudentProfile(StudentProfile studentProfile);

}
