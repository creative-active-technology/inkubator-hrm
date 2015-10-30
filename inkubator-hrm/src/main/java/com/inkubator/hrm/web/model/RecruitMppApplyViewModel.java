/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitMppApplyViewModel implements Serializable {

    private BigInteger recruitMppApplyId;
    private String recruitMppApplyCode;
    private String recruitMppApplyName;
    private Date applyDate;
    private Long jobPositionTotal;
    private BigInteger approvalActivityId;    
    private String activityNumber;
    private Integer approvalStatus;
    private String jsonData;
    private String createdBy;

    public BigInteger getRecruitMppApplyId() {
        return recruitMppApplyId;
    }

    public void setRecruitMppApplyId(BigInteger recruitMppApplyId) {
        this.recruitMppApplyId = recruitMppApplyId;
    }

    

    public String getRecruitMppApplyCode() {
        return recruitMppApplyCode;
    }

    public void setRecruitMppApplyCode(String recruitMppApplyCode) {
        this.recruitMppApplyCode = recruitMppApplyCode;
    }

    public String getRecruitMppApplyName() {
        return recruitMppApplyName;
    }

    public void setRecruitMppApplyName(String recruitMppApplyName) {
        this.recruitMppApplyName = recruitMppApplyName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Long getJobPositionTotal() {
        return jobPositionTotal;
    }

    public void setJobPositionTotal(Long jobPositionTotal) {
        this.jobPositionTotal = jobPositionTotal;
    }

    
    public BigInteger getApprovalActivityId() {
        return approvalActivityId;
    }

    public void setApprovalActivityId(BigInteger approvalActivityId) {
        this.approvalActivityId = approvalActivityId;
    }

    
    public String getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(String activityNumber) {
        this.activityNumber = activityNumber;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    
    
}
