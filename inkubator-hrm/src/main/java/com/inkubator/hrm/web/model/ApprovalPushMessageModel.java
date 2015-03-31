package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class ApprovalPushMessageModel implements Serializable{
	private String approverUserId;
	private String approverFullName;	
	private String requestUserId;
	private String requestFullName;
	private String approvalName;
	
	public String getApproverUserId() {
		return approverUserId;
	}
	public void setApproverUserId(String approverUserId) {
		this.approverUserId = approverUserId;
	}
	public String getRequestUserId() {
		return requestUserId;
	}
	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId;
	}
	public String getApprovalName() {
		return approvalName;
	}
	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}
	public String getApproverFullName() {
		return approverFullName;
	}
	public void setApproverFullName(String approverFullName) {
		this.approverFullName = approverFullName;
	}
	public String getRequestFullName() {
		return requestFullName;
	}
	public void setRequestFullName(String requestFullName) {
		this.requestFullName = requestFullName;
	}	
}
