package com.gui.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;



import com.ejb.services.CountryCodeService;

import com.jpa.entities.CountryCode;



@ViewScoped
@Named("countryController") 
public class CountryCodeController implements Serializable {
	
	private static final long serialVersionUID = -1440759894210785242L;

	private CountryCode countryCode = new CountryCode();	
	
	@EJB
	private CountryCodeService countryCodeService;
	
	private List<CountryCode> countryCodeList;

	private Integer countryId; 
	
	
//	private List<Student> student_rList;
//	
//	private Integer studentId_r;
 
	@PostConstruct
	public void init(){
		loadListOfCountry();
//		setCode(countryCodeService.getList());
	}
	
	
	public void saveCountry() {
		
		countryCodeService.addCountryCode(getCountryCode());
		setCountryCodeList(countryCodeService.getList());
		countryCode = new CountryCode();
	}
	
	public void deleteCountry(CountryCode countryCode){
		getCountryCodeList().remove(countryCode);
		countryCodeService.removeCountryCode(countryCode);;
		
	}
	
	public void editCountry(CountryCode countryCode){
		countryCodeService.updateCountryCode(countryCode);
	}
	
	private void loadListOfCountry(){
		countryCodeList = countryCodeService.getList();
	}
	

	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public List<CountryCode> getCountryCodeList() {
		return countryCodeList;
		
	}

	public void setCountryCodeList(List<CountryCode> countryCodeList) {
		this.countryCodeList = countryCodeList;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

//	public List<CountryCode> getCode() {
//		return code;
//	}
//
//	public void setCode(List<CountryCode> code) {
//		this.code = code;
//	}



}
