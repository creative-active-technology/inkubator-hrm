package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class ApplicantRealizationViewModel implements Serializable {

	private Long applicantId;
	private String name;
	private String phone;
	private String positionApply;
	private String positionLast;
	private String status;
	
	public Long getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPositionApply() {
		return positionApply;
	}
	public void setPositionApply(String positionApply) {
		this.positionApply = positionApply;
	}
	public String getPositionLast() {
		return positionLast;
	}
	public void setPositionLast(String positionLast) {
		this.positionLast = positionLast;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
