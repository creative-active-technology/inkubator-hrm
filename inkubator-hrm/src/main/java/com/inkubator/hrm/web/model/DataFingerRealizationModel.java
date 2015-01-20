package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class DataFingerRealizationModel implements Serializable {

	public String nik;
	public String employeeName;
	public String workingGroupName;
	public Double fingerIn;
	public Double fingerOut;
	public Double webCheckIn;
	public Double webCheckOut;
	
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
	public Double getFingerIn() {
		return fingerIn;
	}
	public void setFingerIn(Double fingerIn) {
		this.fingerIn = fingerIn;
	}
	public Double getFingerOut() {
		return fingerOut;
	}
	public void setFingerOut(Double fingerOut) {
		this.fingerOut = fingerOut;
	}
	public Double getWebCheckIn() {
		return webCheckIn;
	}
	public void setWebCheckIn(Double webCheckIn) {
		this.webCheckIn = webCheckIn;
	}
	public Double getWebCheckOut() {
		return webCheckOut;
	}
	public void setWebCheckOut(Double webCheckOut) {
		this.webCheckOut = webCheckOut;
	}
	
}
