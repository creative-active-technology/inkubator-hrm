package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

public class CareerTransitionSearchParameter extends SearchParameter {
	private String transitionCode;
    private String transitionName;
    private String transitionRole;
    
	public String getTransitionCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "transitionCode")){
			transitionCode = getParameter();
        } else {
        	transitionCode = null;
        }
		return transitionCode;
	}
	public void setTransitionCode(String transitionCode) {
		this.transitionCode = transitionCode;
	}
	public String getTransitionName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "transitionName")){
			transitionName = getParameter();
        } else {
        	transitionName = null;
        }
		return transitionName;
	}
	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}
	public String getTransitionRole() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "transitionRole")){
			transitionRole = getParameter();
        } else {
        	transitionRole = null;
        }
		return transitionRole;
	}
	public void setTransitionRole(String transitionRole) {
		this.transitionRole = transitionRole;
	}
    
    
}
