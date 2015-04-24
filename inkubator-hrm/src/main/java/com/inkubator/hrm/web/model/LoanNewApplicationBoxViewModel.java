package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanNewApplicationBoxViewModel implements Serializable{

	private BigInteger approvalActivityId;
	private Integer loanNewApplicationId;
        private String activityNumber;
	private String empNik;
	private String empName;
	private String loanNumber;
	private BigInteger loanNewTypeId;
	private String loanNewTypeName;	        
        private String approverNik;
        private String approverName;
        private Date applicationDate;
        private Date approvalDate;
	private BigDecimal nominalPrincipal;
	private Integer approvalStatus;	
        private Integer disbursementStatus;
	private String jsonData;

    public BigInteger getApprovalActivityId() {
        return approvalActivityId;
    }

    public void setApprovalActivityId(BigInteger approvalActivityId) {
        this.approvalActivityId = approvalActivityId;
    }

    public Integer getLoanNewApplicationId() {
        return loanNewApplicationId;
    }

    public void setLoanNewApplicationId(Integer loanNewApplicationId) {
        this.loanNewApplicationId = loanNewApplicationId;
    }

    public String getEmpNik() {
        return empNik;
    }

    public void setEmpNik(String empNik) {
        this.empNik = empNik;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }


    public BigInteger getLoanNewTypeId() {
        return loanNewTypeId;
    }

    public void setLoanNewTypeId(BigInteger loanNewTypeId) {
        this.loanNewTypeId = loanNewTypeId;
    }

    public String getLoanNewTypeName() {
        return loanNewTypeName;
    }

    public void setLoanNewTypeName(String loanNewTypeName) {
        this.loanNewTypeName = loanNewTypeName;
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

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Integer getDisbursementStatus() {
        return disbursementStatus;
    }

    public void setDisbursementStatus(Integer disbursementStatus) {
        this.disbursementStatus = disbursementStatus;
    }

    public BigDecimal getNominalPrincipal() {
        return nominalPrincipal;
    }

    public void setNominalPrincipal(BigDecimal nominalPrincipal) {
        this.nominalPrincipal = nominalPrincipal;
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

    public String getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(String activityNumber) {
        this.activityNumber = activityNumber;
    }
    
    
	
}
