package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class ApplicantSearchParameter extends SearchParameter {

	private String name;
    private String email;
    private String cityName;

	public String getName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
			name = getParameter();
        } else {
        	name = null;
        }
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "email")) {
			email = getParameter();
        } else {
        	email = null;
        }
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCityName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "cityName")) {
			cityName = getParameter();
        } else {
        	cityName = null;
        }
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
    
}
