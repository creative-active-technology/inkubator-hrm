package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class DialectSearchParameter extends SearchParameter {
	
	private String dialectCode;	
	private String dialectName;
        
	public String getDialectName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "dialectName")){
			dialectName = getParameter();
		} else {
			dialectName = null;
		}
    	return dialectName;
	}

	public void setDialectName(String dialectName) {
		this.dialectName = dialectName;
	}

	public String getDialectCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "dialectCode")){
			dialectCode = getParameter();
		} else {
			dialectCode = null;
		}
    	return dialectCode;
	}

	public void setDialectCode(String dialectCode) {
		this.dialectCode = dialectCode;
	}

}
