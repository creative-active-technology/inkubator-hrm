package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

public class RecruitAgreementNoticeSearchParameter extends SearchParameter {
	private String empDataName;

	public String getEmpDataName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "empDataName")){
			empDataName = getParameter();
        } else {
        	empDataName = null;
        }
		return empDataName;
	}

	public void setEmpDataName(String empDataName) {
		this.empDataName = empDataName;
	}
	
	
}
