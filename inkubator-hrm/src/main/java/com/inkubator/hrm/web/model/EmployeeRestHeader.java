/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class EmployeeRestHeader implements Serializable {

	private Integer status;
	private String errorMessage;
	private Integer numberOfProfiles;
    private EmployeeRestModel profile;
    
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
    
}
