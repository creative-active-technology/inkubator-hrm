package com.inkubator.hrm.web.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class LeaveImplementationModel implements Serializable{

	private Long id;
	private String numberFilling;
	private Long leaveId;
	private EmpData empData;
	private Date latestLeaveDate;
	private Double remainingLeave;
	private Date startDate;
	private Date endDate;
	private Date fillingDate;
	private String address;
	private String mobilePhone;
	private String materialJobsAbandoned;
	private EmpData temporaryActing;
	private String description;
	private Double actualLeaveTaken;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getNumberFilling() {
		return numberFilling;
	}
	public void setNumberFilling(String numberFilling) {
		this.numberFilling = numberFilling;
	}
	public Long getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public Date getLatestLeaveDate() {
		return latestLeaveDate;
	}
	public void setLatestLeaveDate(Date latestLeaveDate) {
		this.latestLeaveDate = latestLeaveDate;
	}
	public Double getRemainingLeave() {
		return remainingLeave;
	}
	public void setRemainingLeave(Double remainingLeave) {
		this.remainingLeave = remainingLeave;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getFillingDate() {
		return fillingDate;
	}
	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Pattern(regexp = "^[+][\\d() -]+", message = "{errorr_phone}")
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getMaterialJobsAbandoned() {
		return materialJobsAbandoned;
	}
	public void setMaterialJobsAbandoned(String materialJobsAbandoned) {
		this.materialJobsAbandoned = materialJobsAbandoned;
	}
	public EmpData getTemporaryActing() {
		return temporaryActing;
	}
	public void setTemporaryActing(EmpData temporaryActing) {
		this.temporaryActing = temporaryActing;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getActualLeaveTaken() {
		return actualLeaveTaken;
	}
	public void setActualLeaveTaken(Double actualLeaveTaken) {
		this.actualLeaveTaken = actualLeaveTaken;
	}
}
