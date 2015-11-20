package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

public class CareerDisciplineTypeSearchParameter extends SearchParameter{
	private String code;
	private String name;
	
	public String getCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "code")){
			code = getParameter();
		} else {
			code = null;
		}
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
			name = getParameter();
		} else {
			name = null;
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
