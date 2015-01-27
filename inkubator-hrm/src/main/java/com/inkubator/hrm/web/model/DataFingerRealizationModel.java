package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class DataFingerRealizationModel implements Serializable {

	public Long empDataId;
	public String nik;
	public String employeeName;
	public String workingGroupName;
	public Long fingerIn;
	public Long fingerOut;
	public Long webCheckIn;
	public Long webCheckOut;
		
	public Long getEmpDataId() {
		return empDataId;
	}
	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getWorkingGroupName() {
		return workingGroupName;
	}
	public void setWorkingGroupName(String workingGroupName) {
		this.workingGroupName = workingGroupName;
	}
	public Long getFingerIn() {
		return fingerIn;
	}
	public void setFingerIn(Long fingerIn) {
		this.fingerIn = fingerIn;
	}
	public Long getFingerOut() {
		return fingerOut;
	}
	public void setFingerOut(Long fingerOut) {
		this.fingerOut = fingerOut;
	}
	public Long getWebCheckIn() {
		return webCheckIn;
	}
	public void setWebCheckIn(Long webCheckIn) {
		this.webCheckIn = webCheckIn;
	}
	public Long getWebCheckOut() {
		return webCheckOut;
	}
	public void setWebCheckOut(Long webCheckOut) {
		this.webCheckOut = webCheckOut;
	}
	
	
}
