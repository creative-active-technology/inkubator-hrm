/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author deni
 */
public class LoanCanceledModel implements Serializable {

    private Long id;
    private String keterangan;
    private String nomor;
//    private Long empData;
    private Long loanSchema;
    private String loanName;
    private Double nominalPrincipal;
    private Date loanDate;
    private Date loanPaymentDate;
    private Double interestRate;
    private Integer typeOfInterest;
    private Integer termin;
    private String approvalActivityNumber;
    private Integer statusPencairan;
    private Date approvalTime;
    private EmpData empData;
    private Long approvalActivityId;
    private String code;
    private Date cancelledDate;
    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public Long getLoanSchema() {
        return loanSchema;
    }

    public void setLoanSchema(Long loanSchema) {
        this.loanSchema = loanSchema;
    }

    public Double getNominalPrincipal() {
        return nominalPrincipal;
    }

    public void setNominalPrincipal(Double nominalPrincipal) {
        this.nominalPrincipal = nominalPrincipal;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getLoanPaymentDate() {
        return loanPaymentDate;
    }

    public void setLoanPaymentDate(Date loanPaymentDate) {
        this.loanPaymentDate = loanPaymentDate;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTypeOfInterest() {
        return typeOfInterest;
    }

    public void setTypeOfInterest(Integer typeOfInterest) {
        this.typeOfInterest = typeOfInterest;
    }

    public Integer getTermin() {
        return termin;
    }

    public void setTermin(Integer termin) {
        this.termin = termin;
    }

    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }

    public Integer getStatusPencairan() {
        return statusPencairan;
    }

    public void setStatusPencairan(Integer statusPencairan) {
        this.statusPencairan = statusPencairan;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

	public Long getApprovalActivityId() {
		return approvalActivityId;
	}

	public void setApprovalActivityId(Long approvalActivityId) {
		this.approvalActivityId = approvalActivityId;
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
    
    
}

