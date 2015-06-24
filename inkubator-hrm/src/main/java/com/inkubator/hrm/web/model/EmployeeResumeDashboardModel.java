package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author rizkykojek
 */
public class EmployeeResumeDashboardModel implements Serializable {

	public Long totalEmployee;
	public Long todayLeave;
	public Long todayBusinessTravel;
	public BigDecimal yesterdayAttendance;
	public BigDecimal todayAttendance;
	public String nearestBirthday;
	
	public Long getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	public Long getTodayLeave() {
		return todayLeave;
	}
	public void setTodayLeave(Long todayLeave) {
		this.todayLeave = todayLeave;
	}
	public Long getTodayBusinessTravel() {
		return todayBusinessTravel;
	}
	public void setTodayBusinessTravel(Long todayBusinessTravel) {
		this.todayBusinessTravel = todayBusinessTravel;
	}	
	public BigDecimal getYesterdayAttendance() {
		return yesterdayAttendance;
	}
	public void setYesterdayAttendance(BigDecimal yesterdayAttendance) {
		this.yesterdayAttendance = yesterdayAttendance;
	}
	public BigDecimal getTodayAttendance() {
		return todayAttendance;
	}
	public void setTodayAttendance(BigDecimal todayAttendance) {
		this.todayAttendance = todayAttendance;
	}
	public String getNearestBirthday() {
		return nearestBirthday;
	}
	public void setNearestBirthday(String nearestBirthday) {
		this.nearestBirthday = nearestBirthday;
	}		
	
}
