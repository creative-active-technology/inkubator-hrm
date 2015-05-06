/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.RecruitMppPeriod;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitMppApplyModel implements Serializable {

    private Long id;
    private String recruitMppApplyCode;
    private String recruitMppApplyName;
    private Date applyDate;
    private String reason;
    private String recruitMppApplyFileName;
    private String uploadPath;
    private Long mppPeriodId;    
    private RecruitMppPeriod selectedRecruitMppPeriod;
    private Integer approvalStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public Long getMppPeriodId() {
        return mppPeriodId;
    }

    public void setMppPeriodId(Long mppPeriodId) {
        this.mppPeriodId = mppPeriodId;
    }

    public String getRecruitMppApplyFileName() {
        return recruitMppApplyFileName;
    }

    public void setRecruitMppApplyFileName(String recruitMppApplyFileName) {
        this.recruitMppApplyFileName = recruitMppApplyFileName;
    }

    public RecruitMppPeriod getSelectedRecruitMppPeriod() {
        return selectedRecruitMppPeriod;
    }

    public void setSelectedRecruitMppPeriod(RecruitMppPeriod selectedRecruitMppPeriod) {
        this.selectedRecruitMppPeriod = selectedRecruitMppPeriod;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    
   
    
}
