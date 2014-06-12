package com.inkubator.hrm.web.model;

import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.AttendanceStatus;

/**
 *
 * @author rizkykojek
 */
public class LeaveModel {

	private Long id;
    private String name;
    private String code;
    private String description;
    private String dayType;
    private String calculation;
    private List<AttendanceStatus> attendanceStatusList;
    private Long attendanceStatusId;
    private String periodBase;
    private String availability;
    private Date availabilityAtSpecificDate;
    private Boolean isTakingLeaveToNextYear;
    private Integer maxTakingLeaveToNextYear;
    private Integer backwardPeriodLimit;
    private Boolean isAllowedMinus;
    private Integer maxAllowedMinus;
    private Integer effectiveFrom;
    private Integer submittedLimit;
    private Integer approvalLevel;
    private Boolean isQuotaReduction;
    private String endOfPeriod;
    private Integer endOfPeriodMonth;
    private Boolean isOnlyOncePerEmployee;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDayType() {
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}	
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}	
	public List<AttendanceStatus> getAttendanceStatusList() {
		return attendanceStatusList;
	}
	public void setAttendanceStatusList(List<AttendanceStatus> attendanceStatusList) {
		this.attendanceStatusList = attendanceStatusList;
	}
	public Long getAttendanceStatusId() {
		return attendanceStatusId;
	}
	public void setAttendanceStatusId(Long attendanceStatusId) {
		this.attendanceStatusId = attendanceStatusId;
	}
	public String getPeriodBase() {
		return periodBase;
	}
	public void setPeriodBase(String periodBase) {
		this.periodBase = periodBase;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public Date getAvailabilityAtSpecificDate() {
		return availabilityAtSpecificDate;
	}
	public void setAvailabilityAtSpecificDate(Date availabilityAtSpecificDate) {
		this.availabilityAtSpecificDate = availabilityAtSpecificDate;
	}
	public Boolean getIsTakingLeaveToNextYear() {
		return isTakingLeaveToNextYear;
	}
	public void setIsTakingLeaveToNextYear(Boolean isTakingLeaveToNextYear) {
		this.isTakingLeaveToNextYear = isTakingLeaveToNextYear;
	}
	public Integer getMaxTakingLeaveToNextYear() {
		return maxTakingLeaveToNextYear;
	}
	public void setMaxTakingLeaveToNextYear(Integer maxTakingLeaveToNextYear) {
		this.maxTakingLeaveToNextYear = maxTakingLeaveToNextYear;
	}
	public Integer getBackwardPeriodLimit() {
		return backwardPeriodLimit;
	}
	public void setBackwardPeriodLimit(Integer backwardPeriodLimit) {
		this.backwardPeriodLimit = backwardPeriodLimit;
	}
	public Boolean getIsAllowedMinus() {
		return isAllowedMinus;
	}
	public void setIsAllowedMinus(Boolean isAllowedMinus) {
		this.isAllowedMinus = isAllowedMinus;
	}
	public Integer getMaxAllowedMinus() {
		return maxAllowedMinus;
	}
	public void setMaxAllowedMinus(Integer maxAllowedMinus) {
		this.maxAllowedMinus = maxAllowedMinus;
	}
	public Integer getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(Integer effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public Integer getSubmittedLimit() {
		return submittedLimit;
	}
	public void setSubmittedLimit(Integer submittedLimit) {
		this.submittedLimit = submittedLimit;
	}
	public Integer getApprovalLevel() {
		return approvalLevel;
	}
	public void setApprovalLevel(Integer approvalLevel) {
		this.approvalLevel = approvalLevel;
	}
	public Boolean getIsQuotaReduction() {
		return isQuotaReduction;
	}
	public void setIsQuotaReduction(Boolean isQuotaReduction) {
		this.isQuotaReduction = isQuotaReduction;
	}
	public String getEndOfPeriod() {
		return endOfPeriod;
	}
	public void setEndOfPeriod(String endOfPeriod) {
		this.endOfPeriod = endOfPeriod;
	}
	public Integer getEndOfPeriodMonth() {
		return endOfPeriodMonth;
	}
	public void setEndOfPeriodMonth(Integer endOfPeriodMonth) {
		this.endOfPeriodMonth = endOfPeriodMonth;
	}
	public Boolean getIsOnlyOncePerEmployee() {
		return isOnlyOncePerEmployee;
	}
	public void setIsOnlyOncePerEmployee(Boolean isOnlyOncePerEmployee) {
		this.isOnlyOncePerEmployee = isOnlyOncePerEmployee;
	}
    
}