package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class CountrySearchParameter extends SearchParameter {
	
	private String countryCode;	
	private String countryName;
        
	public String getCountryName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "countryName")){
			countryName = getParameter();
		} else {
			countryName = null;
		}
    	return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "countryCode")){
			countryCode = getParameter();
		} else {
			countryCode = null;
		}
    	return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
