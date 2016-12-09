package com.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name=CountryCode.ENTITY_NAME)
public class CountryCode {
	public static final String ENTITY_NAME = "countrycode";
	private Integer countryId;
//	private String countryName;
	private String countryCode;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
	@Column(nullable=false)
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
//	@Column(nullable=false)
//	public String getCountryName() {
//		return countryName;
//	}
//	
//	public void setCountryName(String countryName) {
//		this.countryName = countryName;
//	}
	
}
