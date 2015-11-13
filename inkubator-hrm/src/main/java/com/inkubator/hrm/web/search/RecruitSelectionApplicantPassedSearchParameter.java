package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitSelectionApplicantPassedSearchParameter extends SearchParameter {
	
	private String candidateName;	
	private String jabatanName;	
	private String departmentName;
	
	public String getCandidateName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "candidateName")){
			candidateName = getParameter();
		} else {
			candidateName = null;
		}
		return candidateName;
	}
	
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public String getJabatanName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanName")){
			jabatanName = getParameter();
		} else {
			jabatanName = null;
		}
		return jabatanName;
	}
	
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	
	public String getDepartmentName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "departmentName")){
			departmentName = getParameter();
		} else {
			departmentName = null;
		}
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
