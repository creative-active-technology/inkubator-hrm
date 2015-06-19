package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class DepAttendanceRealizationViewModel implements Serializable {

    private BigInteger departmentId;
    private Integer weekNumber;
    private BigInteger attendanceSchedule;
    private BigInteger attendanceReal;
    private BigDecimal attendancePercentage;
    private String departmentName;
	public BigInteger getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(BigInteger departmentId) {
		this.departmentId = departmentId;
	}
	
	public Integer getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}
	public BigDecimal getAttendancePercentage() {
		return attendancePercentage;
	}
	public void setAttendancePercentage(BigDecimal attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public BigInteger getAttendanceSchedule() {
		return attendanceSchedule;
	}
	public void setAttendanceSchedule(BigInteger attendanceSchedule) {
		this.attendanceSchedule = attendanceSchedule;
	}
	public BigInteger getAttendanceReal() {
		return attendanceReal;
	}
	public void setAttendanceReal(BigInteger attendanceReal) {
		this.attendanceReal = attendanceReal;
	}    

	
    
}
