package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;


public class WorkingTimeDeviationDetailModel implements Serializable{
	
	private Date fromPeriod;
	private Date toPeriod;
	private String nikAndFullName;
	private Long totalWorkingTime;
	private String totalPlusMinus;
	private Integer totalMarginHour;
	private Integer totalMarginMinutes;
	private Date date;
	private String workingGroup;
	private Date checkInTime;
	private Date checkOutTime;
	private String diffTime;
	
	
	public Integer getTotalMarginHour() {
		return totalMarginHour;
	}
	public void setTotalMarginHour(Integer totalMarginHour) {
		this.totalMarginHour = totalMarginHour;
	}
	public Integer getTotalMarginMinutes() {
		return totalMarginMinutes;
	}
	public void setTotalMarginMinutes(Integer totalMarginMinutes) {
		this.totalMarginMinutes = totalMarginMinutes;
	}
	public Date getFromPeriod() {
		return fromPeriod;
	}
	public void setFromPeriod(Date fromPeriod) {
		this.fromPeriod = fromPeriod;
	}
	public Date getToPeriod() {
		return toPeriod;
	}
	public void setToPeriod(Date toPeriod) {
		this.toPeriod = toPeriod;
	}
	public String getNikAndFullName() {
		return nikAndFullName;
	}
	public void setNikAndFullName(String nikAndFullName) {
		this.nikAndFullName = nikAndFullName;
	}

	public Long getTotalWorkingTime() {
		return totalWorkingTime;
	}
	public void setTotalWorkingTime(Long totalWorkingTime) {
		this.totalWorkingTime = totalWorkingTime;
	}
	public String getTotalPlusMinus() {
		return totalPlusMinus;
	}
	public void setTotalPlusMinus(String totalPlusMinus) {
		this.totalPlusMinus = totalPlusMinus;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getWorkingGroup() {
		return workingGroup;
	}
	public void setWorkingGroup(String workingGroup) {
		this.workingGroup = workingGroup;
	}
	public Date getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public String getDiffTime() {
		return diffTime;
	}
	public void setDiffTime(String diffTime) {
		this.diffTime = diffTime;
	}
	
	
	
}
