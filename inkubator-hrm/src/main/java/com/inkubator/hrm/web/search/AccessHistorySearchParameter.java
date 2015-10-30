package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;


public class AccessHistorySearchParameter extends SearchParameter{
	private String userId;

	public String getUserId() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "userId")){
			userId = getParameter();
		} else{
			userId = null;
		}
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
