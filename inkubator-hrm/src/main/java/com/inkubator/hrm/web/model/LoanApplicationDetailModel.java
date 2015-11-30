package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanApplicationDetailModel implements Serializable {

    private Integer id;
    private String approvalActivityNumber;
    private String loanNumber;
    private Long approvalActivityId;
    private String nik;
    private String empDataName;
    private String loanTypeName;
    private Date submissionDate;
    private Date approvedDate;
    private Integer termin;
    private Double loanNominal;
    private String approverNik;
    private String approverName;
    private Integer approvalStatus;
    private Integer disbursementStatus;
    
    

    public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getApprovalActivityNumber() {
		return approvalActivityNumber;
	}

	public void setApprovalActivityNumber(String approvalActivityNumber) {
		this.approvalActivityNumber = approvalActivityNumber;
	}

	public Long getApprovalActivityId() {
		return approvalActivityId;
	}

	public void setApprovalActivityId(Long approvalActivityId) {
		this.approvalActivityId = approvalActivityId;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getEmpDataName() {
		return empDataName;
	}

	public void setEmpDataName(String empDataName) {
		this.empDataName = empDataName;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Integer getTermin() {
		return termin;
	}

	public void setTermin(Integer termin) {
		this.termin = termin;
	}

	public Double getLoanNominal() {
		return loanNominal;
	}

	public void setLoanNominal(Double loanNominal) {
		this.loanNominal = loanNominal;
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

	public Integer getDisbursementStatus() {
		return disbursementStatus;
	}

	public void setDisbursementStatus(Integer disbursementStatus) {
		this.disbursementStatus = disbursementStatus;
	}

   

}
