package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

public class WorkingTimeDeviationListDetailModel implements Serializable{
	private Long employeeId;
	private Date workingDate;
	private String workingGroupName;
	private Date fingerIn;
	private Date fingerOut;
	private Long deviationTime;
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Date getWorkingDate() {
		return workingDate;
	}
	public void setWorkingDate(Date workingDate) {
		this.workingDate = workingDate;
	}
	public String getWorkingGroupName() {
		return workingGroupName;
	}
	public void setWorkingGroupName(String workingGroupName) {
		this.workingGroupName = workingGroupName;
	}
	public Date getFingerIn() {
		return fingerIn;
	}
	public void setFingerIn(Date fingerIn) {
		this.fingerIn = fingerIn;
	}
	public Date getFingerOut() {
		return fingerOut;
	}
	public void setFingerOut(Date fingerOut) {
		this.fingerOut = fingerOut;
	}
	public Long getDeviationTime() {
		return deviationTime;
	}
	public void setDeviationTime(Long deviationTime) {
		this.deviationTime = deviationTime;
	}

	
}