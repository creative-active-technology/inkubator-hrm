package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class BankSearchParameter extends SearchParameter {
	
	private String bankCode;	
	private String bankName;
        
	public String getBankName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "bankName")){
			bankName = getParameter();
		} else {
			bankName = null;
		}
    	return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "bankCode")){
			bankCode = getParameter();
		} else {
			bankCode = null;
		}
    	return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
