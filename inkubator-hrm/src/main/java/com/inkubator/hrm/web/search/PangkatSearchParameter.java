package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class PangkatSearchParameter extends SearchParameter {

	private String pangkatCode;
	private String pangkatName;
	
	public String getPangkatCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "pangkatCode")){
			pangkatCode = getParameter();
		} else {
			pangkatCode = null;
		}
		return pangkatCode;
	}
	public void setPangkatCode(String pangkatCode) {
		this.pangkatCode = pangkatCode;
	}
	public String getPangkatName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "pangkatName")){
			pangkatName = getParameter();
		} else {
			pangkatName = null;
		}
		return pangkatName;
	}
	public void setPangkatName(String pangkatName) {
		this.pangkatName = pangkatName;
	}
	
}
