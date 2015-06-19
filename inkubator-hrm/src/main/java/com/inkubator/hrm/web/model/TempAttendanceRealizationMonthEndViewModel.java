/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author rizkykojek
 */
public class TempAttendanceRealizationMonthEndViewModel implements Serializable {
    
	private Long departmentId;
    private String departmentName;
    private Long totalEmployee;
    private Double attendance;
    private Long attendanceDaysSchedule;
    private Long attendanceDaysPresent;
    private Double overtime;
    
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Long getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	public Double getAttendance() {
		BigDecimal present = new BigDecimal(attendanceDaysPresent);
		BigDecimal schedule = new BigDecimal(attendanceDaysSchedule);
		return present.multiply(new BigDecimal(100)).divide(schedule,2,RoundingMode.HALF_UP).doubleValue();
	}
	public void setAttendance(Double attendance) {
		this.attendance = attendance;
	}	
	public Double getOvertime() {
		return overtime;
	}
	public void setOvertime(Double overtime) {
		this.overtime = overtime;
	}
	public Long getAttendanceDaysSchedule() {
		return attendanceDaysSchedule;
	}
	public void setAttendanceDaysSchedule(Long attendanceDaysSchedule) {
		this.attendanceDaysSchedule = attendanceDaysSchedule;
	}
	public Long getAttendanceDaysPresent() {
		return attendanceDaysPresent;
	}
	public void setAttendanceDaysPresent(Long attendanceDaysPresent) {
		this.attendanceDaysPresent = attendanceDaysPresent;
	}
	
    
}
