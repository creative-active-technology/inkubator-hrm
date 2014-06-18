package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class NationalitySearchParameter extends SearchParameter {
	
	private String nationalityCode;	
	private String nationalityName;
        
	public String getNationalityName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "nationalityName")){
			nationalityName = getParameter();
		} else {
			nationalityName = null;
		}
    	return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getNationalityCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "nationalityCode")){
			nationalityCode = getParameter();
		} else {
			nationalityCode = null;
		}
    	return nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

}
