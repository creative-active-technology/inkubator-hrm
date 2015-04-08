package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author rizkykojek
 */
public class RmbsCancelationModel implements Serializable {
	
	private Long id;
	private String code;
    private Date cancelledDate;
    private String reason;
    private Long approvalActivityId;
    private EmpData empData;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getApprovalActivityId() {
		return approvalActivityId;
	}
	public void setApprovalActivityId(Long approvalActivityId) {
		this.approvalActivityId = approvalActivityId;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	
    
}
