package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class AppraisalProgramDistributionViewModel implements Serializable {

	private Long programId;
	private String programName;
	private Date programStartDate;
	private Date programEndDate;
	private Long totalEmployee;
	
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public Date getProgramStartDate() {
		return programStartDate;
	}
	public void setProgramStartDate(Date programStartDate) {
		this.programStartDate = programStartDate;
	}
	public Date getProgramEndDate() {
		return programEndDate;
	}
	public void setProgramEndDate(Date programEndDate) {
		this.programEndDate = programEndDate;
	}
	public Long getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	
	
}
