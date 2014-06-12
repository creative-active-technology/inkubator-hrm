package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class LeaveSchemeSearchParameter extends SearchParameter {
	
	private String name;	
	private String code;
	private String leaveName;

	public String getName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
			name = getParameter();
		} else {
			name = null;
		}
    	return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getLeaveName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "leaveName")){
			leaveName = getParameter();
		} else {
			leaveName = null;
		}
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
}
