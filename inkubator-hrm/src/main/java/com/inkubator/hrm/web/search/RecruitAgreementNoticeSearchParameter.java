package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

public class RecruitAgreementNoticeSearchParameter extends SearchParameter {
	private String empDataName;
	private String jabatan;
	private String lastEducation;
	

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

	public String getJabatan() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "jabatan")){
			jabatan = getParameter();
        } else {
        	jabatan = null;
        }
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getLastEducation() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "lastEducation")){
			lastEducation = getParameter();
        } else {
        	lastEducation = null;
        }
		return lastEducation;
	}

	public void setLastEducation(String lastEducation) {
		this.lastEducation = lastEducation;
	}
	
	
}
