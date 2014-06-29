package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class InstitutionEducationSearchParameter extends SearchParameter {
	
	private String institutionEducationCode;	
	private String institutionEducationName;
        private String cityName;
        
        public String getCityName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "cityName")){
			cityName = getParameter();
		} else {
			cityName = null;
		}
    	return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}        
                
	public String getInstitutionEducationName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "institutionEducationName")){
			institutionEducationName = getParameter();
		} else {
			institutionEducationName = null;
		}
    	return institutionEducationName;
	}

	public void setInstitutionEducationName(String institutionEducationName) {
		this.institutionEducationName = institutionEducationName;
	}

	public String getInstitutionEducationCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "institutionEducationCode")){
			institutionEducationCode = getParameter();
		} else {
			institutionEducationCode = null;
		}
    	return institutionEducationCode;
	}

	public void setInstitutionEducationCode(String institutionEducationCode) {
		this.institutionEducationCode = institutionEducationCode;
	}

}
