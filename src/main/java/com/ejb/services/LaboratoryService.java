package com.ejb.services;

import java.math.BigDecimal;
import java.util.List;

import com.jpa.entities.Laboratory;

public interface LaboratoryService {

	void addLaboratory(Laboratory laboratory);

	void updateLaboratory(Laboratory laboratory);
	
	List<Laboratory> getLaboratories(Laboratory laboratory);
	
	void removeLaboratory(Laboratory laboratory);
	
	List<Laboratory> getLaboratoriesAccordingtoGrade(int id);
	
	BigDecimal getLabScoreByGrade(int id);
}
