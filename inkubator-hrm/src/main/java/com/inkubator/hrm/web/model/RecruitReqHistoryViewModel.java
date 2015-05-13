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
public class RecruitReqHistoryViewModel implements Serializable {

    private Long rhaId;
    private String recHireCode;   
    private Date efectiveDate;
    private Integer totalReq;
    private String jabatanCode;
    private String jabatanName;
    private String requestBy;
    private String nikRequester;
    private String nameRequester;
    private BigInteger approvalActivityId;    
    private String activityNumber;
    private Integer approvalStatus;
    private String jsonData;

    public Long getRhaId() {
        return rhaId;
    }

    public void setRhaId(Long rhaId) {
        this.rhaId = rhaId;
    }

    public String getRecHireCode() {
        return recHireCode;
    }

    public void setRecHireCode(String recHireCode) {
        this.recHireCode = recHireCode;
    }

    public Date getEfectiveDate() {
        return efectiveDate;
    }

    public void setEfectiveDate(Date efectiveDate) {
        this.efectiveDate = efectiveDate;
    }

    public Integer getTotalReq() {
        return totalReq;
    }

    public void setTotalReq(Integer totalReq) {
        this.totalReq = totalReq;
    }

    public String getJabatanCode() {
        return jabatanCode;
    }

    public void setJabatanCode(String jabatanCode) {
        this.jabatanCode = jabatanCode;
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
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

    public String getNikRequester() {
        return nikRequester;
    }

    public void setNikRequester(String nikRequester) {
        this.nikRequester = nikRequester;
    }

    public String getNameRequester() {
        return nameRequester;
    }

    public void setNameRequester(String nameRequester) {
        this.nameRequester = nameRequester;
    }
    
    
    
}
