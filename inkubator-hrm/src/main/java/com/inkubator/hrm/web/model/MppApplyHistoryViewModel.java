package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class MppApplyHistoryViewModel implements Serializable {

	private BigInteger approvalActivityId;
	private BigInteger recruitMppApplyId;
	private String activityNumber;
	private String requesterNik;
	private String requesterName;
	private String approverNik;
	private String approverName;
	private Integer approvalStatus;
	private Long jobPositionTotal;
	private String mppApplyName;
	private Date mppApplyPeriodStart;
	private Date mppApplyPeriodEnd;
	private String jsonData;
	private Boolean isAlreadyPersisted;
	
	public BigInteger getApprovalActivityId() {
		return approvalActivityId;
	}
	public void setApprovalActivityId(BigInteger approvalActivityId) {
		this.approvalActivityId = approvalActivityId;
	}
	public BigInteger getRecruitMppApplyId() {
		return recruitMppApplyId;
	}
	public void setRecruitMppApplyId(BigInteger recruitMppApplyId) {
		this.recruitMppApplyId = recruitMppApplyId;
	}
	public String getActivityNumber() {
		return activityNumber;
	}
	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}
	public String getRequesterNik() {
		return requesterNik;
	}
	public void setRequesterNik(String requesterNik) {
		this.requesterNik = requesterNik;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public String getApproverNik() {
		return approverNik;
	}
	public void setApproverNik(String approverNik) {
		this.approverNik = approverNik;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Long getJobPositionTotal() {
		return jobPositionTotal;
	}
	public void setJobPositionTotal(Long jobPositionTotal) {
		this.jobPositionTotal = jobPositionTotal;
	}
	public String getMppApplyName() {
		return mppApplyName;
	}
	public void setMppApplyName(String mppApplyName) {
		this.mppApplyName = mppApplyName;
	}	
	public Date getMppApplyPeriodStart() {
		return mppApplyPeriodStart;
	}
	public void setMppApplyPeriodStart(Date mppApplyPeriodStart) {
		this.mppApplyPeriodStart = mppApplyPeriodStart;
	}
	public Date getMppApplyPeriodEnd() {
		return mppApplyPeriodEnd;
	}
	public void setMppApplyPeriodEnd(Date mppApplyPeriodEnd) {
		this.mppApplyPeriodEnd = mppApplyPeriodEnd;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public Boolean getIsAlreadyPersisted() {
		boolean isAlreadyPersisted = recruitMppApplyId != null && recruitMppApplyId.intValue() != 0;
		return isAlreadyPersisted;
	}
	public void setIsAlreadyPersisted(Boolean isAlreadyPersisted) {
		this.isAlreadyPersisted = isAlreadyPersisted;
	}		
	
}
