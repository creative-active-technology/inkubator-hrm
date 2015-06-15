/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author rizkykojek
 */
public class EmployeeRestHeader implements Serializable {

	private Integer status;
	private String errorMessage;
	@JsonInclude(value=Include.NON_NULL)
	private Integer numberOfProfiles;
	@JsonInclude(value=Include.NON_NULL)
    private EmployeeRestModel profile;
	@JsonInclude(value=Include.NON_NULL)
	private List<EmployeeRestModel> profiles;
    
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getNumberOfProfiles() {
		return numberOfProfiles;
	}
	public void setNumberOfProfiles(Integer numberOfProfiles) {
		this.numberOfProfiles = numberOfProfiles;
	}
	public EmployeeRestModel getProfile() {
		return profile;
	}
	public void setProfile(EmployeeRestModel profile) {
		this.profile = profile;
	}
	public List<EmployeeRestModel> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<EmployeeRestModel> profiles) {
		this.profiles = profiles;
	}
    
}
