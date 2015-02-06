package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Taufik Hidayat
 */
public class ReportPermitHistoryModel implements Serializable {

    private String nikWithFullName;
    private String numberFilling;
    private String permitClassification;
    private Date requestTime;
    private Date approvalTime;
    private String approvedBy;
    private Date startDate;
    private Date endDate;

    public String getNikWithFullName() {
        return nikWithFullName;
    }

    public void setNikWithFullName(String nikWithFullName) {
        this.nikWithFullName = nikWithFullName;
    }

    public String getNumberFilling() {
        return numberFilling;
    }

    public void setNumberFilling(String numberFilling) {
        this.numberFilling = numberFilling;
    }

    public String getPermitClassification() {
        return permitClassification;
    }

    public void setPermitClassification(String permitClassification) {
        this.permitClassification = permitClassification;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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

    
    
    

}
