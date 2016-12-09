package com.ejb.services;

import com.jpa.entities.UserSubject;

public interface UserSubjectService {
	
	UserSubject findUserIdNo(Integer userIdNo);
	UserSubject findSubjectIdNo(Integer subjectIdNo);
	UserSubject findSubject(Integer subjectIdNo);
}
