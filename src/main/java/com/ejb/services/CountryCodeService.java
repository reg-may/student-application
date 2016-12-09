package com.ejb.services;

import java.util.List;

import com.jpa.entities.CountryCode;

public interface CountryCodeService {
	
	List<CountryCode> getList();

	void addCountryCode(CountryCode countryCode);

	void removeCountryCode(CountryCode countryCode);

	void updateCountryCode(CountryCode countryCode);
	
//	List<CountryCode> getCodeList();
	CountryCode findCode(Integer countryId);
}
